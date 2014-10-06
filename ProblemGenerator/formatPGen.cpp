#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

int main(){
  
  cout<<"Program to format the obtained problems properly"<<endl;
  
  ifstream fIn;
  fIn.open("pset64k.txt");
  ofstream fOutE,fOutA;
  
  fOutE.open("eset64k.txt",ios_base::app);
  fOutA.open("aset64k.txt",ios_base::app);
  
  string line;
  while(getline(fIn,line)){
    stringstream lineS(line);
    string problem="",answer="",temp="";
    for(int i=0;i<3;i++){
      lineS>>temp;
      problem += temp + " ";
    }
   
    lineS>>answer;
    fOutE<<"\""<<problem<<"\""<<","<<endl;
    fOutA<<answer<<","<<endl;
  }
  
  fIn.close();
  fOutE.close();
  fOutA.close();
  
  return 0;
  
}
