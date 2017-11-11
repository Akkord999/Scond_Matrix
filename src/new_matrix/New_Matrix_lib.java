package new_matrix;

import java.util.Arrays;

public class New_Matrix_lib {
	public double [][] getProduct(double a[][] , double b[][]){
		double answer[][] = new double [a.length][b[0].length];
		double t[][] = this.getTranspose(b);
		for (int i= 0;i < a.length; i++) {
			for (int j = 0; j < b[0].length;j++) {
				answer[i][j] = this.getInnerProduct(a[i],t[j]);
			}
		}
		return answer;
	}

	public double [][] getTranspose(double a[][]) {
		double t[][] = new double [a[0].length][a.length];
		for (int i = 0;i < a[0].length;i++) {
			for (int j = 0; j < a.length; j++) {
				t[i][j] = a[j][i];
			}
		}
		return t;
	}

	public double getInnerProduct(double a[],double b[]) {
    	double answer = 0;
    	for (int i = 0;i < a.length; i++) {
    		answer += a[i] * b[i]; 
    	}
    	return answer;
	}
	
	public double getCofactor(double a[][] ) {
		double cofactor = 0;
		
		if (a.length == 2) {
			cofactor = a[0][0]*a[1][1]-a[0][1]*a[1][0];
		}
		else{
			double tmp[][] = new double[a.length-1][a[0].length-1];
			int index = 0;
			
			for (int i = 0;i < a.length;i++) {
				int p = 0,q = 0;
				for (int j = 0;j < a.length; j++) {
					if (i == j) continue;
					for (int k = 0;k < a[0].length; k++) {
						if(index == k) continue;
						tmp[p][q++] = a[j][k];
					}
					p++;
			        q = 0;
				}
				System.out.println("tmp["+i+"] = " + Arrays.deepToString(tmp));
			    cofactor += a[i][index]*Math.pow(-1,(i + 1)+(index + 1))*this.getCofactor(tmp);		
		   }
	    }
	   return cofactor;	
     }
	
	public double [][]getInverse(double [][] a){
		double answer [][] = new double [a.length][a[0].length];
		double tmp[][] = new double[a.length-1][a[0].length-1];
		double determinant = this.getCofactor(a);
		
		for (int i = 0;i < a.length;i++) {
			for (int j = 0;j < a[0].length;j++) {
				int p = 0,q = 0;
				for (int k = 0;k < a.length; k++) {
					if (i == k) continue;
					for (int s = 0;s < a[0].length; s++) {
						if(j == s) continue;
						tmp[p][q] = a[k][s];
						q++;
					}
					p++;
			        q = 0;
				}
			
			    answer[i][j] = Math.pow(-1, i + 1 + j + 1)*this.getCofactor(tmp)/determinant;		
		   }
	    }
		
		answer = this.getTranspose(answer);
		return answer;
	}
	
	public double [] getSolution(double [][] a,double [] b) {
		double ans [] = new double [b.length] ;
		a = this.getInverse(a);
		
		for(int i = 0;i < a.length;i++) {
			for(int j = 0; j < a.length;j++) {
				ans[i] += a[i][j] * b[j];
			}
		}
		
		return ans;
	}
	
	
	
	//クラメルの公式作りかけ
	/*public double [] getSolution(double [][] a,double [] b) {
		double x = this.getCofactor(a);
		double ans [] = new double [b.length] ;
		double box[][]  = new double [a.length][a[0].length] ; ;
		for(int i = 0;i < a.length;i++) {
			box  =  a ;
			for(int j = 0; j < a.length;j++) {
				box[j][i] = b[j];
			}
			ans [i] = this.getCofactor(box) / x;
			
		}
		return ans;
	}*/
	
	//ガウスの消去法作りかけ
	/*public double [] getSolution(double [][] a,double [] b) {
		
		int i,j,k;
		double d;
		double ans [] = new double [b.length];
		double N [][] = new double [a.length][a[0].length + 1];
		for ( i = 0; i < a.length; i++) 
			N[i][a[0].length + 1] = b[i];
		for ( k = 0;k < a.length - 1;k++) {
			for( i = k + 1; i < a.length; i++) {
				d = a[i][k] / a[k][k];
				for( j = k + 1; j <= a.length; j++) 
					a[i][j] -= a[k][j] * d;
			}	
		}
		
		for ( i = a.length - 1; i >= 0; i--) {
			d = a[i][a.length];
			for ( j = i + 1; j < a.length; j++) 
				d -= a[i][j] * a[j][a.length];
		    a [i][a.length] = d / a[i][i];
		}
		
		for ( k = 0; k < a.length; k++)
			ans[k] = a[k][a.length];
		return ans;	
	}*/
	

}
