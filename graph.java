package projeto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class graph implements Serializable {
	
	private static final long serialVersionUID = 1L;
	int dim;
	double m[][];
	
	public graph(int dim) {//cria o graph como matrizes
		super();
		this.dim = dim;
		this.m= new double [dim][dim];
		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m.length;j++) {
				m[i][j]=-1;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Graph1 [dim=" + dim + ", m=" + Arrays.deepToString(m) + "]";
	}
	public void addEdge(int o, int d, double p) throws Exception{
		if (0<=o && o<dim && 0<=d && d<dim)
			m[o][d]=p;
		else
			throw new Exception("Error: invalid origin or destiny");
	}
	
	public boolean edgeQ(int o, int d) {
		return m[o][d]!=-1;
	}
	
	public double weigth(int o, int d) {
		return m[o][d];
	}
	
	public ArrayList<Integer> offspring (int o){
		ArrayList<Integer> off  = new ArrayList<Integer>();
		for (int j = 0; j<m.length; j++) {
			if (m[o][j]!=-1) off.add(j);
		}
		return off;
	}
	
	public graph MST() throws Exception {
		graph F = new graph(dim);
		ArrayList<Integer> I = new ArrayList<Integer>();//Lista de nós percorridos
		I.add(0);
		ArrayList<Integer> Q = new ArrayList<Integer>();//Lista de nós por percorrer
		for (int k = 1; k < dim; k++) {
			Q.add(k);
		}
		while (!Q.isEmpty()) {
			double max=-1;
			int imax=0;
			int jmax=0;
			for (int i : I) {
				for (int j : Q) {
					if (max<=m[i][j]) {
						max=m[i][j];
						imax=i;
						jmax=j;
					}
				}
			}
			F.addEdge(imax,jmax,max);
			F.addEdge(jmax,imax,max);
			Q.remove(Q.indexOf(jmax));
			I.add(jmax);
		}
		return F;
	}
	
	
	public ArrayList<int[]> dirgraph(int o) throws Exception{//breath first 
		ArrayList<int[]> arestas= new ArrayList<int[]>();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(o);
		while(!queue.isEmpty()) {
			int tp=queue.removeFirst(); 
			if(!visited.contains(tp)) {
				visited.add(tp);
				ArrayList<Integer> offs = offspring(tp); 
				offs.removeAll(visited);
				for (int i = 0; i < offs.size(); i++) {
					arestas.add(new int[] {tp,offs.get(i)});
				}
				queue.addAll(offspring(tp));
			}
		}
		return arestas;
	}
	
	/*public static void main(String[] args) {
		try {
		graph g = new graph(5);
		g.addEdge(0, 1, 0);
		g.addEdge(0, 2, 5);
		g.addEdge(0, 3, 2);
		g.addEdge(0, 4, 1);
		g.addEdge(1, 0, 0);
		g.addEdge(2, 0, 5);
		g.addEdge(3, 0, 2);
		g.addEdge(4, 0, 1);
		
		g.addEdge(1, 2, 0);
		g.addEdge(1, 3, 0);
		g.addEdge(1, 4, 0);
		g.addEdge(2, 1, 0);
		g.addEdge(3, 1, 0);
		g.addEdge(4, 1, 0);
	
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 2);
		g.addEdge(3, 2, 3);
		g.addEdge(4, 2, 2);
		
		g.addEdge(3, 4, 1);
		g.addEdge(4, 3, 1);
		
		System.out.println(g.dirgraph(0).toString());
		System.out.println(g.MST().toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
