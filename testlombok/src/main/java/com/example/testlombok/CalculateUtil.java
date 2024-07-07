package com.example.testlombok;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculateUtil {

    public static int calMagic() {
        return (int)( Math.random() * 100 );
    }
}
