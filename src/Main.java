import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	static ArrayList<Location> directions = new ArrayList<>();
	static Location origin=null;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] line = reader.readLine().split(" ");
		origin = new Location(Long.parseLong(line[0]), Long.parseLong(line[1]), Long.parseLong(line[2]));
		
		int n = Integer.parseInt(reader.readLine());
		for (int i=0;i<n;i++) {
			line = reader.readLine().split(" ");
			Location enemy = new Location(Long.parseLong(line[0]), Long.parseLong(line[1]), Long.parseLong(line[2]));
			
			enemy = simplify(enemy);
			if (directions.isEmpty()) {
				directions.add(enemy);
			} else {
				boolean hasDirection = false;
				for (Location loc : directions)  {
					if (loc.x==enemy.x && loc.y==enemy.y && loc.z==enemy.z) {
						hasDirection = true;
						break;
					}
				} 
				if (!hasDirection) {
					directions.add(enemy);
				}
			}
		}
		System.out.println(directions.size());
	}
	

	private static Location simplify(Location loc) {
		// TODO Auto-generated method stub
		long x = loc.x-origin.x;
		long y = loc.y-origin.y;
		long z = loc.z-origin.z;
		
		long gcd = getGCD(x,y);
		gcd = getGCD(gcd,z);
		return new Location(x/gcd, y/gcd, z/gcd);
	}

	private static long getGCD(long x, long y) {
		// TODO Auto-generated method stub
		long a,b;
		if (x==y) return x;
		
		if (x>y) {
			a=x;
			b=y;
		} else {
			a=y;
			b=x;
		}
		
		while (a % b != 0) {
			b = a % b;
			a = b;
		}
		return b;
	}
	

}


class Location {
	long x;
	long y;
	long z;
	
	public Location(Long x, Long y, Long z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
}