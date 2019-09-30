#include<iostream>
#include<iomanip>
#include<deque>
#include<list>
#include<iterator>
#include<string>
#include<algorithm>
#include <ctype.h>
/*本程序实现从键盘读入任意单词，保存到List<string>容器中，然后再把容器中的单词复制到
list<string>容器中，并将列表中的内容排成升序，最后输出*/

using namespace std;

int main(){
	deque<string>words;
	string s;
	while (true){
                               //输入单词，用空格分隔单词，结束按enter,然后按Ctrl+Z，然后再按enter键结束输入
		if ((cin >> s).eof()){
			cin.clear();
			break;
		}
		words.push_back(s);
	}
	list<string> stringlist(begin(words), end(words));//复制到list<string>中
                //升序排序
	stringlist.sort([](string &s1, string &s2){
		return s1 < s2;
	});
                //输出容器中的元素
	for (auto & str : stringlist){
		cout << str << ' ';
	}
	system("pause");
	return 0;
}