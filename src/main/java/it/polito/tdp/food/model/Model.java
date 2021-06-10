package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	Graph<String,DefaultWeightedEdge> grafo;
	
	int pesoMax;
	List<String> listaRicorsione;
	
	public String doCreaGrafo(double cal) {
		FoodDao dao = new FoodDao();
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		List<String> vertici= new ArrayList<>(dao.getVertici(cal));
		Graphs.addAllVertices(grafo, vertici);
		List<Adiacenti> archi = new ArrayList<>(dao.getAdiacenti(cal));
		for(Adiacenti a: archi) {
			if(!vertici.contains(a.getPortion1()))
				grafo.addVertex(a.getPortion1());
			if(!vertici.contains(a.getPortion2()))
				grafo.addVertex(a.getPortion2());
			Graphs.addEdge(grafo, a.getPortion1(), a.getPortion2(), a.getPeso());
		}
		String result = "";
		if(this.grafo==null) {
			result ="Grafo non creato";
			return result;
		}
		result = "Grafo creato con :\n# "+this.grafo.vertexSet().size()+" VERTICI\n# "+this.grafo.edgeSet().size()+" ARCHI\n";
		return result;
		
	}
	
	
	public String doCorrelate(String portion) {
		String result = "\n\nI tipi di porzione correlati a "+portion+ " sono :\n";
		List<String> vicini = new ArrayList<>(Graphs.neighborListOf(grafo, portion));
		for(String str: vicini){
			result+= str+"   "+(int) grafo.getEdgeWeight(grafo.getEdge(portion, str))+"\n";
		}
		return result;
	}
	
	
	public String doCammino(int N,String portion ) {
		pesoMax=0;
		listaRicorsione = new ArrayList<String>();
		List<String> parziale = new ArrayList<>();
		for(String s: Graphs.neighborListOf(grafo, portion)) {
			int peso = (int) grafo.getEdgeWeight(grafo.getEdge(portion, s));
			parziale.add(s);
			cerca(N,parziale,peso,1);
			parziale.remove(0);
		}
		String result ="\nIl cammino massimo partendo da "+portion+" di "+ N+ " passi è :\n";
		
		if(listaRicorsione.size()==0) {
			result+="INESISTENTE";
			return result;
			}
		for(String s :listaRicorsione) {
			result+=s+"\n";
		}
		return result+"Il peso totale del cammino trovato è "+pesoMax+" !!!!!!!";
		
		
		
	}
	
	public void cerca(int N, List<String> parziale, int lunghezza, int passo) {
		if(passo==N) {
			if(lunghezza>pesoMax) {
				pesoMax = lunghezza ;
				listaRicorsione = new ArrayList<String>(parziale);
			}
			return;
		}else {
			String str = parziale.get(passo-1);
			for(String s: Graphs.neighborListOf(grafo, str)) {
				if(!parziale.contains(s)) {
					int peso = (int) grafo.getEdgeWeight(grafo.getEdge(str, s));
					parziale.add(s);
					cerca(N,parziale,lunghezza+peso,passo+1);
					parziale.remove(passo);
				}
			}
		}
		
	}
	
	
	public List<String> getVertici(){
		List<String> vertici= new ArrayList<>(grafo.vertexSet());
		return vertici;
	}
}
