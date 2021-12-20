package hello.servlet.basic.domain;

import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.pow;

//lombok
@Getter @Setter
public class Member {

    //index
    private Long id;
    private String username;
    private int height;
    private int weight;
    private double bmi;

    public Member(){}

    public Member( String username, int height, int weight) {
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.bmi = calbmi(height,weight);

    }

    /*
    BMI계산 산출식 : (남자 기준) 체중/신장^2
     */
    public double calbmi(int height, int weight){
        double calheight = (double)height/100;
        return weight / pow(calheight,2);
    }
}
