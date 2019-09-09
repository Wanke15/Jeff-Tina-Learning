#include<iostream>
#include<iomanip>
#include<array>
#include<iterator>
#include<algorithm>

using namespace std;

template<typename t>
void prin(int n, array<t, 45> a){
	int shang= a.size() / n;
	int yu = a.size() % n;
	for (int i = 0; i < shang; i++){
		for (int j = 0; j < n; j++)
			cout << a[i*n + j] << ' ';
		cout << endl;
	}
	for (int k = 0; k < yu; k++)
		cout << a[shang*n + k] << ' ';
}

int main(){
	array<int, 45> a{};
	int b = 0, c = 1;
	generate( begin(a)+2, end(a),[b,c]()mutable{
		long long int d = b + c;
		b = c;
		c = d;
		return d;
	});
	prin<int>(8, a);
	system("pause");
	return 0;
}