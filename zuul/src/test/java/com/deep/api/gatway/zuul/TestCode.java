package com.deep.api.gatway.zuul;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//solution1(21,29);
		int a[]={0,0,0,0,0,0,0};
		//System.out.println(solution3(a));
		solution2(2,7);

	}
	
	public static int solution1(int A, int B) {
        // write your code in Java SE 8
		
		int count =0;
		int product=0;
        if(B<A)
            return 0;
        int initial = findInitialNum(A);
        for(int i=initial; i<B; i++){
        	product = i*(i+1);
        	if(product<=B){
        	    count++;
        	}else{
        	    break;
        	}
        }
        System.out.println(count);
        return count;
        
    }
	
	private static int findInitialNum(int a){
        int initialNum=1;
        int product=0;
        for(int i=1; i<a; i++){
        	product = i*(i+1);
        	if(product<a){
        	    continue;
        	}else{
        		initialNum=i;
        	    break;
        	}
        }
        return initialNum;
    }

	public static String[] solution2(int N, int K) {
        if (N == 0) {
            return new String[] {""};
        }
        ArrayList<String> result = new ArrayList<String>();
        for (String p : solution2(N - 1, K - 1)) {
            for (char l : new char[] {'a', 'b', 'c'}) {
                int pLen = p.length();
                if (pLen == 0 || p.charAt(pLen - 1) != l) {
                    result.add(p + l);
                }
            }
        }
        int prefSize = Math.min(result.size(), K);
        return result.subList(0, prefSize).toArray(new String[prefSize]);
    }
	
	public static int solution3(int[] A) {
        // write your code in Java SE 8
		int count=0;
		if(Arrays.stream(A).filter(a-> a==0).count()==A.length){
			return 1;
		}
		for(int i=0; i<A.length;i++){
			if(A[i]==0){
				count++;
				i++;
			}
			count+= findZeroSum(i,A);
		}
		
		return count;
    }

	private static int findZeroSum(int i, int[] a) {
		int sum=0;
		int count=0;
		
		for(int j=i;j<a.length;j++){
			sum+=a[j];
			if(sum==0){
				count++;
			}
		}
		return count;
	}
	
}
