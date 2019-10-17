package arjuna.tpi.guiauto.helpers;

public class Screen {
	
	public static class Point{
		private int x;
		private int y;
		
		protected Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int[] getLocation() {
			return new int[] {x,y};
		}
	}
	
	public static class Offset extends Point{
		
		private Offset(int x, int y) {
			super(x,y);
		}
		
	}
	
	public static Point xy(int x, int y) {
		return new Point(x,y);
	}
	
	public static Offset offset(int x, int y) {
		return new Offset(x,y);
	}

}
