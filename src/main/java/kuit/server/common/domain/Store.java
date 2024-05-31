package kuit.server.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private String name;
    private String category;
    private String phoneNumber;
    private long minOrderPrice;
    private double rate;
    private long saveCount;
    private long reviewCount;
}
