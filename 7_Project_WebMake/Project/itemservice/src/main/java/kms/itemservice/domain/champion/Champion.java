package kms.itemservice.domain.champion;

import lombok.Data;

//프로젝트 규모가 작으므로 사용
@Data
public class Champion {
    private Long id;
    private String chamName;
    private int price; //가격은 NULL이 들어오면 안된다.
    private String comment;

    //생성자
    public Champion(){}

    //생성자
    public Champion(String chamName, int price, String comment) {
        this.chamName = chamName;
        this.price = price;
        this.comment = comment;
    }
}
