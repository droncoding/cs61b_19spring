import java.util.Scanner;

public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int number = in.readInt();
        double r = in.readDouble();
        Body bodies[] = new Body[number];
        for (int i=0; i<number;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String image = in.readString();
            Body body = new Body(xxPos,yyPos,xxVel,yyVel,mass,image);
            bodies[i] = body;
        }
        
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);

        double dt = Double.parseDouble(args[1]);

        String filename = args[2];

        double r = readRadius(filename);
    
        Body[] bodies = readBodies(filename);

        StdDraw.setScale(-r, r);
        StdDraw.picture(0,0,"images/starfield.jpg");

        for(Body onebody : bodies){
            onebody.draw();
        };

        StdDraw.enableDoubleBuffering();
        
        double time = 0;

        while(time <T){
            
            double xForces[] = new double[bodies.length];
            double yForces[] = new double[bodies.length];

            for (int i=0; i<bodies.length;i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                
            }
            for (int i=0; i<bodies.length;i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
                
            }
            
            StdDraw.setScale(-r, r);
            StdDraw.picture(0,0,"images/starfield.jpg");

            for(Body onebody : bodies){
                onebody.draw();
            };
            
            StdDraw.show();
            StdDraw.pause(10);
            time+= dt;
        }


        

        
    }

    

}
