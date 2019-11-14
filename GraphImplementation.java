import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph
{
	private int[][] matrix;
	private int vertices;

	public GraphImplementation(int vertices)
	{
		this.vertices = vertices;
		matrix = new int[vertices][vertices];
	}

	public void addEdge(int v1, int v2) throws Exception
	{
		if ((v1 < 0 || v1 >= vertices) || (v2 < 0 || v2 >= vertices))
			throw new Exception();
		matrix[v1][v2] = 1;
	}

	public List<Integer> topologicalSort()
	{
		List<Integer> list = new ArrayList<Integer>();
		int [] sum = new int[vertices];

		for (int i=0; i < vertices; i++)
		{
			for (int j=0; j < vertices; j++)
				sum[i]+=matrix[j][i];
		}
		for (int i=0; i < vertices; i++)
		{
			int next=findZero(sum);
			if (next == -1)
			{
				return list;
			}
			list.add(next);
			System.out.println(next);
			sum[next]=-1;
			for (int j=0; j<vertices; j++)
				sum[j]-=matrix[next][j];
		}
		System.out.println();
		return list;
	}

	public List<Integer> neighbors(int vertex) throws Exception
	{
		if (vertex < 0 || vertex >= vertices)
			throw new Exception();

		List<Integer> list = new ArrayList<Integer>();
		for (int i=0; i<vertices; i++)
		{
			if (matrix[vertex][i] == 1)
				list.add(i);
		}
		return list;
	}

	private int findZero(int [] a)
	{
		for (int i=0; i<a.length; i++)
		{
			if (a[i]==0)
				return i;
		}
		return -1;
	}
}