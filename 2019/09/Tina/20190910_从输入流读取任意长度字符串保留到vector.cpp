#include<iterator>
#include<string>
#include<algorithm>
#include<sstream>

using namespace std;

int main(){
	vector<string> citys;
	cout << "please enter city,separate by space" << endl;
	string cityname;
	string str;
	getline(cin,cityname);
	stringstream ss(cityname);
	while (ss >> str){
		citys.push_back(str);
	}
	sort(citys.begin(),citys.end());
	for_each(begin(citys), end(citys), [](string s){
		cout << s << ' ';
	});
	system("pause");
	return 0;
}
