#include <cstdio>
#include <iostream>
#include <string>
using namespace std;

int main(){
	string text;
	string key;
	char output;
	bool option;
	
	
	cout<<"Type 1 for Encrypt , Type 0 for Decrypt : ";
	cin>>option;
	
	if (option)
	{
		cout<<"Please Enter your Plain Text(Capital Only)(English Only) :\n"; 
		cin>>text;
		cout<<"Please Enter your Key (Same Length with text)(Capital Only)(English Only):\n"; 
		cin>>key;
		cout<<"Cipher Text :\n"; 
	
		for (int i = 0; i < text.length(); i++)
		{
			output = ((text[i]-'A') + (key[i]-'A') + 26 ) % 26 + 'A' ;
			cout<<output;
		} 	
	}
	else
	{
		cout<<"Please Enter your Cipher Text(Capital Only)(English Only) :\n"; 
		cin>>text;
		cout<<"Please Enter your Key (Same Length with text)(Capital Only)(English Only):\n"; 
		cin>>key;
		cout<<"Plain Text :\n"; 
		for (int i = 0; i < text.length(); i++)
		{
			output = (((text[i]-'A') - (key[i]-'A') + 26 ) % 26  + 'A') ; 
			cout<<output;
		} 
	}
}

