#include<iostream>
#include<iomanip>
#include<stack>
#include<iterator>
#include<string>
#include<algorithm>
#include <ctype.h>

/*本程序实现用一个stack<T>容器适配器将从键盘中输入的一行文字反向，
程序应该输出反向后的结果，并判断和原始字符串是否回文（忽略空格和标点）*/

using namespace std;

int main(){
	stack<char> s;
	char c;
	string str;
	cout << "请输入一行字符串，按#和Enter键结束输入" << endl;
	while (true){
		c = cin.get();//不会忽视空格，以回车停止输入，回车键会被留在输入流中，
		if (c=='#')
			break;//如果输入“#”，则终止输入
		s.push(c);
		str += c;
	}
	while (!s.empty()){
		cout<<s.top();
		s.pop();
	}
	//去掉字符串中的标点符号
	str.erase(
		remove_if(str.begin(), str.end(), static_cast<int(*)(int)>(&ispunct)),
		str.end());
	//去掉字符串中的空格
	str.erase(
		remove_if(str.begin(), str.end(), static_cast<int(*)(int)>(&isspace)),
		str.end());
	int l = str.length();
	int i = 0;
	while (i < l/2){
		if (str[i] != str[l - i - 1]){
			cout << "输入字符串不是回文" << endl;
			i++;
			break;
		}
		else
			i++;
	}
	if (i==l/2)
		cout << "输入字符串是回文" << endl;
	system("pause");
	return 0;
}