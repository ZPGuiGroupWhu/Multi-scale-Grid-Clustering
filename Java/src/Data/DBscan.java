package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBscan {
	private double radius;
    private int minPts;

    public DBscan(double radius,int minPts) {
        this.radius = radius;
        this.minPts = minPts;
    }

    public void process(ArrayList<Point> points) {
        int size = points.size();
        int idx = 0;
        int cluster = 1;
        while (idx<size) {
            Point p = points.get(idx++);
            //choose an unvisited point
            if (!p.getVisit()) {
                p.setVisit(true);//set visited
                ArrayList<Point> adjacentPoints = getAdjacentPoints(p, points);
                //set the point which adjacent points less than minPts noised
                if (adjacentPoints != null && adjacentPoints.size() < minPts) {
                    p.setNoised(true);
                } else {
                    p.setCluster(cluster);
                    for (int i = 0; i < adjacentPoints.size(); i++) {
                        Point adjacentPoint = adjacentPoints.get(i);
                        //only check unvisited point, cause only unvisited have the chance to add new adjacent points
                        if (!adjacentPoint.getVisit()) {
                            adjacentPoint.setVisit(true);
                            ArrayList<Point> adjacentAdjacentPoints = getAdjacentPoints(adjacentPoint, points);
                            //add point which adjacent points not less than minPts noised
                            if (adjacentAdjacentPoints != null && adjacentAdjacentPoints.size() >= minPts) {
                                adjacentPoints.addAll(adjacentAdjacentPoints);
                            }
                        }
                        //add point which doest not belong to any cluster
                        if (adjacentPoint.getCluster() == 0) {
                            adjacentPoint.setCluster(cluster);
                            //set point which marked noised before non-noised
                            if (adjacentPoint.getNoised()) {
                                adjacentPoint.setNoised(false);
                            }
                        }
                    }
                    cluster++;
                }
            }
        }
        for (Point p:points) {
    		if(p.getCluster()%7==0){
				p.setCluster((int) (p.getCluster()/7));
			}
			if(p.getCluster()%7==1){
				p.setCluster((int) (Math.floor(cluster/7)+(p.getCluster()-1)/7));
			}
			if(p.getCluster()%7==2){
				p.setCluster((int) (Math.floor(cluster/7)*2+(p.getCluster()-2)/7));
			}
			if(p.getCluster()%7==3){
				p.setCluster((int) (Math.floor(cluster/7)*3+(p.getCluster()-3)/7));
			}
			if(p.getCluster()%7==4){
				p.setCluster((int) (Math.floor(cluster/7)*4+(p.getCluster()-4)/7));
			}
			if(p.getCluster()%7==5){
				p.setCluster((int) (Math.floor(cluster/7)*5+(p.getCluster()-5)/7));
			}
			if(p.getCluster()%7==6){
				p.setCluster((int) (Math.floor(cluster/7)*6+(p.getCluster()-6)/7));
			}
    	}
    }

    private ArrayList<Point> getAdjacentPoints(Point centerPoint,ArrayList<Point> points) {
        ArrayList<Point> adjacentPoints = new ArrayList<Point>();
        for (Point p:points) {
            //include centerPoint itself
            double distance = centerPoint.getDistance(p);
            if (distance<=radius) {
                adjacentPoints.add(p);
            }
        }
        return adjacentPoints;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
    	ArrayList<Point> points = new ArrayList<Point>();
    	List<String> rstream = ReadCSV.GetData("C:\\Users\\Administrator\\Desktop\\hubei.csv");
    	long t1=System.currentTimeMillis();
    	for(int i=0;i<rstream.size();i++){
			String lngstr = rstream.get(i).substring(0,rstream.get(i).lastIndexOf(","));
			String latstr = rstream.get(i).substring(rstream.get(i).lastIndexOf(",")+1,rstream.get(i).length());
			Double lng = Double.valueOf(lngstr.toString());
			Double lat = Double.valueOf(latstr.toString());
			points.add(new Point(lng,lat));
		} 
        DBscan dbscan = new DBscan(0.00078,2);
        dbscan.process(points);
        String filePath = "C:\\Users\\Administrator\\Desktop\\dbscan1.csv";
	    try{
	        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath),false));
	        for (Point p:points) {
	        	writer.write(p+"\r\n");
	        }
	        writer.close();
	    }catch(Exception e){
	        e.printStackTrace();
	    }
        long t2=System.currentTimeMillis(); //èŽ·å–ç»“æŸæ—¶é—´
	    System.out.println("t2-t1: "+(t2-t1)+"ms"); 
    }  //²âÊÔDBSCAN
}
