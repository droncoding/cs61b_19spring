public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName =img;
    }
    
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body body2){
        double dx = xxPos - body2.xxPos;
        double dy = yyPos - body2.yyPos;
        double rsquare = dx*dx+dy*dy;
        double r = Math.sqrt(rsquare);
        return r;
    }

    public double calcForceExertedBy(Body body2){
        double G = 6.67e-11;
        double F = G * mass * body2.mass/Math.pow(calcDistance(body2), 2);
        return F;
    }

    public double calcForceExertedByX(Body body2){
        double Fx = calcForceExertedBy(body2)*(body2.xxPos-xxPos)/calcDistance(body2);
        return Fx;
    }

    public double calcForceExertedByY(Body body2){
        double Fx = calcForceExertedBy(body2)*(body2.yyPos-yyPos)/calcDistance(body2);
        return Fx;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double nFx =0;
        for (Body onebody:allBodys){
            if(! this.equals(onebody)){
                nFx += calcForceExertedByX(onebody);
            }
        }
        return nFx;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double nFy =0;
        for (Body onebody:allBodys){
            if(! this.equals(onebody)){
                nFy += calcForceExertedByY(onebody);
            }
        }
        return nFy;
    }

    public void update(double dt, double fX, double fY){
        double anx = fX/mass;
        double any = fY/mass;
        xxVel = xxVel + dt*anx;
        yyVel = yyVel + dt*any;
        xxPos = xxPos + dt* xxVel;
        yyPos = yyPos + dt* yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
