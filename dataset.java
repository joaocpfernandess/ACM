package projeto;


import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Dataset implements Serializable {
	
	private static final long serialVersionUID = 1L;
	ArrayList<int []> dados = new ArrayList<int []>();
	int[] dom =null; 
	
	public Dataset() {
		super();
		this.dados = new ArrayList<int[]>();
		this.dom = null;
	}
	
	public int[] dom() {
		return dom;
	}
	
	public double Size() {
		return dados.size();
	}
	
	public Dataset(String file) throws IOException {
		
		BufferedReader reader = new BufferedReader (new FileReader(file));
		String line;
		while ((line=reader.readLine())!=null) {
			//System.out.println(line);
			String [] sl = line.split(",");
			int a[] = new int[sl.length];
			for (int i = 0; i < a.length; i++) {
				a[i]=Integer.parseInt(sl[i]);
			}
			Add(a);
		}
		reader.close();
	}
	
	public void Add(int [] a) {
		if (dom==null) dom = new int [a.length];
		for (int i = 0; i < dom.length; i++) {
			if (dom[i]<a[i]) dom[i]=a[i];
		}
		dados.add(a);
	}
	
	
	public int Count(int[] v, int[] a) {
		int r=0;
		for (int i = 0; i < dados.size(); i++) {
			boolean t = true;
			for (int j = 0; j < v.length; j++) {
				if (dados.get(i)[v[j]]!=a[j]) {
					t = false;
				}
			}
			if (t) {
				r++;
			}
		}
		return r;
	}
	
	public Dataset Fiber(int i) {
		Dataset d = new Dataset();
		int tam = dom.length-1;
		for (int j = 0; j < dados.size(); j++) {
			if (dados.get(j)[tam]==i) {
				d.Add(Arrays.copyOfRange(dados.get(j), 0, tam));
			}
		}
		d.dom=Arrays.copyOfRange(dom, 0, tam);
		return d;
	}
	
	String toStringaux() {
		String s="";
		for (int [] a: dados) {
			s=s+"\n"+Arrays.toString(a);
		}
		return s;
	}
	
	@Override
	public String toString() {
		return "Amostra [dados=" + toStringaux() + "\n dominios=" + Arrays.toString(dom) + "]";
	}

	/*public static void main(String[] args) {
		try {
			Dataset B;
			B = new Dataset("bcancer.csv");
			System.out.println(B);
			Dataset C;
			C=B.Fiber(1);
			System.out.println(C);
			System.out.println(C.Count(new int[] {1,2},new int[]{0,1}));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
