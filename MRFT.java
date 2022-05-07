package projeto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class MRFT implements Serializable {
	
	private static final long serialVersionUID = 1L;
	ArrayList<int[]> mrft;
	Dataset data;
	double delta;
	ArrayList<double[][]> phitens;
	
	public MRFT(Dataset d, double del) {
		try {
			this.data=d;
			this.mrft=ChowLiu().dirgraph(0);
			this.delta=del;
			this.phitens = new ArrayList<double[][]>();
			for (int i = 0; i < mrft.size(); i++) {
				phitens.add(new double[d.dom[mrft.get(i)[0]]+1][d.dom[mrft.get(i)[1]]+1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public graph ChowLiu() throws Exception {
		graph F= new graph(data.dom().length);
		for (int i = 0; i < data.dom().length; i++) {
			for (int j = 0; j < data.dom().length; j++) {
				if (i!=j) {
					F.addEdge(i, j, I(new int[] {i,j}));
				}
			}
		}
		return F.MST();
	}
	
	private double I(int[] v) {
		double soma=0;
		double prij=0;
		double pri=0;
		double prj=0;
		double m= data.Size();
		for (int i=0; i<=data.dom()[v[0]]; i++) {
			for (int j = 0; j <=data.dom()[v[1]]; j++) {
				prij=data.Count(v, new int[] {i,j})/m;
				pri=data.Count(new int[] {v[0]},new int[] {i})/m;
				prj=data.Count(new int[] {v[1]},new int[] {j})/m;
				if(prij!=0) {
					soma+=prij*Math.log(prij/(pri*prj));
				}
			}
		}
		return soma;
	}
	
	private double phi(int[] v, int[] a) {
		if (Arrays.equals(v,mrft.get(0))) {
			return (data.Count(v,a)+delta)/(data.dados.size()+delta*(data.dom[v[0]]+1)*(data.dom[v[1]]+1));
		} else {
			return (data.Count(v,a)+delta)/(data.Count(new int[] {v[0]}, new int[] {a[0]})+delta*(data.dom[v[1]]+1));
		}
	}
	
	public double Prob(int[] v) {
		double a=1;
		int j=0;
		for (int[] i: mrft) {
			if (phitens.get(j)[v[i[0]]][v[i[1]]]==0) {
				phitens.get(j)[v[i[0]]][v[i[1]]]=phi(new int[] {i[0],i[1]},new int[] {v[i[0]],v[i[1]]});
			}
			a=a*phitens.get(j)[v[i[0]]][v[i[1]]];
			j++;
		}
		return a;
	}
	
	String toStringaux() {
		String s="";
		for (int [] a: mrft) {
			s=s+"\n"+Arrays.toString(a);
		}
		return s;
	}
	
	public String toStringaux2() {
		String s="";
		for (double[][] a: phitens) {
			s=s+"\n"+Arrays.deepToString(a);
		}
		return s;
	}

	@Override
	public String toString() {
		return "MRFT [mrft=" + toStringaux() + ", data=" + data + ", phitens=" + toStringaux2() + "]";
	}
	

	/*public static void main(String[] args) {
		try {
		Dataset B = new Dataset("bcancer.csv");
		MRFT m=new MRFT(B.Fiber(1),0.2);
		System.out.println(m.Prob(new int[] {1,0,1,3,2,0,0,2,1,0}));
		System.out.println(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}

