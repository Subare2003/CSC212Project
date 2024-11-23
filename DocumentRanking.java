
public class DocumentRanking {

	public int identification;
	public int standing;
	
	public DocumentRanking(int Standing, int id) {
		
		standing=Standing;
		identification=id;
	}
	
	public void display() {
		System.out.println();
		System.out.println(standing+"       "+identification);
		
		
	}
}
