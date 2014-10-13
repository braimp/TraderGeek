
#include <iostream>
#include <string>
#include <fstream>
#include <cmath>
#include <cstdlib>
#include <sstream>
using namespace std;


void generateForAddition(string fname);
void generateForSubtraction(string fname);
void generateForAdditionInt(string fname);
void generateForSubtractionInt(string fname);
void generateForMultiplication(string fname);
void generateForDivision(string fname);
void generateForMultiplicationInt(string fname);
void generateForDivisionInt(string fname);

  string convertToString(int num);
  string insertPoint(string numStr);

int main(){

  cout<<"Program to generate problems for mathematical test Trader Geek"<<endl;

  string fileOut;
  cout<<"Enter filename to write problems to : ";
  cin>>fileOut;

  for(int i=0;i<250;i++){
    generateForAddition(fileOut);
    generateForSubtraction(fileOut);
    generateForMultiplication(fileOut);
    generateForDivision(fileOut);
    generateForAdditionInt(fileOut);
    generateForSubtractionInt(fileOut);
    generateForMultiplicationInt(fileOut);
    generateForDivisionInt(fileOut);
  }

  return 0;

}

void generateForAddition(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);

    int num1,num2;
    int scope = rand()%4;

    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        case 1:
           num1 = rand()%1000;
           num2 = rand()%1000;
           break;
        case 2:
           num1 = rand()%5000;
           num2 = rand()%1000;
           break;
        case 3:
           num1 = rand()%500;
           num2 = rand()%100;
           break;
        default:
           num1 = rand()%500;
           num2 = rand()%250;
           break;
    }

    double num1D, num2D;
    string num1Str = convertToString(num1);
    string num2Str = convertToString(num2);
    num1Str = insertPoint(num1Str);
    num2Str = insertPoint(num2Str);
    stringstream num1StrS(num1Str);
    stringstream num2StrS(num2Str);
    num1StrS>>num1D;
    num2StrS>>num2D;

    double ans = num1D + num2D;

    stringstream equationStr;
    equationStr<<num1Str<<" + "<<num2Str<<" "<<ans;

    string eqn = equationStr.str();
    f<<eqn<<endl;
    f.close();
}


  string convertToString(int num){
    stringstream numStr;
    numStr<<num;
    return numStr.str();
  }

  string insertPoint(string numStr){
    int len = numStr.length();
    int pointPos = rand()%(len) + 1;
    string numRet = numStr.substr(0,pointPos-1) + "." + numStr.substr(pointPos-1);
    if(pointPos==1)
        numRet = "0" + numRet;
    return numRet;
  }



void generateForAdditionInt(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);

    int num1,num2;
    int scope = rand()%4;

    switch(scope){
    case 0:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        case 1:
           num1 = rand()%1000;
           num2 = rand()%1000;
           break;
        case 2:
           num1 = rand()%5000;
           num2 = rand()%1000;
           break;
        case 3:
           num1 = rand()%500;
           num2 = rand()%100;
           break;
        default:
           num1 = rand()%500;
           num2 = rand()%250;
           break;
    }

    int ans = num1 + num2;

    stringstream equationStr;
    equationStr<<num1<<" + "<<num2<<" "<<ans;

    string eqn = equationStr.str();
    f<<eqn<<endl;
    f.close();
}

void generateForSubtractionInt(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);


    int num1,num2;
    int scope = rand()%4;

    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        case 1:
           num1 = rand()%1000;
           num2 = rand()%1000;
           break;
        case 2:
           num1 = rand()%5000;
           num2 = rand()%1000;
           break;
        case 3:
           num1 = rand()%500;
           num2 = rand()%100;
           break;
        default:
           num1 = rand()%500;
           num2 = rand()%250;
           break;
    }


    int ans = num1 - num2;

    stringstream equationStr;
    equationStr<<num1<<" - "<<num2<<" "<<ans;

    string eqn = equationStr.str();
    f<<eqn<<endl;
    f.close();
}

void generateForSubtraction(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);


    int num1,num2;
    int scope = rand()%4;

    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        case 1:
           num1 = rand()%1000;
           num2 = rand()%1000;
           break;
        case 2:
           num1 = rand()%5000;
           num2 = rand()%1000;
           break;
        case 3:
           num1 = rand()%500;
           num2 = rand()%100;
           break;
        default:
           num1 = rand()%500;
           num2 = rand()%250;
           break;
    }

    double num1D, num2D;
    string num1Str = convertToString(num1);
    string num2Str = convertToString(num2);
    num1Str = insertPoint(num1Str);
    num2Str = insertPoint(num2Str);
    stringstream num1StrS(num1Str);
    stringstream num2StrS(num2Str);
    num1StrS>>num1D;
    num2StrS>>num2D;

    double ans = num1D - num2D;

    stringstream equationStr;
    equationStr<<num1Str<<" - "<<num2Str<<" "<<ans;

    string eqn = equationStr.str();
    f<<eqn<<endl;
    f.close();
}

void generateForMultiplication(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);

    int num1,num2;
    int scope = rand()%5;
    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%50;
           break;
        case 1:
           num1 = rand()%100;
           num2 = rand()%50;
           break;
        case 2:
           num1 = rand()%50;
           num2 = rand()%10*10;
           break;
        case 3:
           num1 = rand()%10*10;
           num2 = rand()%25*10;
           break;
        case 4:
           num1 = rand()%75;
           num2 = rand()%25;
           break;
        default:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
    }

    double num1D, num2D;
    string num1Str = convertToString(num1);
    string num2Str = convertToString(num2);
    num1Str = insertPoint(num1Str);
    num2Str = insertPoint(num2Str);
    stringstream num1StrS(num1Str);
    stringstream num2StrS(num2Str);
    num1StrS>>num1D;
    num2StrS>>num2D;

    double ans = num1D * num2D;

    stringstream equationStr;
    equationStr<<num1Str<<" * "<<num2Str<<" "<<ans;

    string eqn = equationStr.str();
    f<<eqn<<endl;
    f.close();
}

void generateForMultiplicationInt(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);

    int num1,num2;
    int scope = rand()%5;

    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%50;
           break;
        case 1:
           num1 = rand()%100;
           num2 = rand()%50;
           break;
        case 2:
           num1 = rand()%50;
           num2 = rand()%10*10;
           break;
        case 3:
           num1 = rand()%10*10;
           num2 = rand()%25*10;
           break;
        case 4:
           num1 = rand()%75;
           num2 = rand()%25;
           break;
        default:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
    }

    int ans = num1 * num2;

    stringstream equationStr;
    equationStr<<num1<<" * "<<num2<<" "<<ans;

    string eqn = equationStr.str();
    f<<eqn<<endl;
    f.close();
}

void generateForDivision(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);

    int num1,num2;
    int scope = rand()%4;

    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        case 1:
           num1 = rand()%100;
           num2 = rand()%50;
           break;
        case 2:
           num1 = rand()%100;
           num2 = rand()%75;
           break;
        case 3:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        default:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
    }


    double num1D, num2D;
    string num1Str = convertToString(num1);
    string num2Str = convertToString(num2);
    num1Str = insertPoint(num1Str);
    num2Str = insertPoint(num2Str);
    stringstream num1StrS(num1Str);
    stringstream num2StrS(num2Str);
    num1StrS>>num1D;
    num2StrS>>num2D;

    double ans = num1D * num2D;

    stringstream equationStr;
    equationStr<<ans<<" / "<<num2Str<<" "<<num1Str;

    string eqn = equationStr.str();
    f<<eqn<<endl;

    f.close();

}

void generateForDivisionInt(string fname){
    ofstream f;
    f.open(fname.c_str(),ios_base::app);

    int num1,num2;
    int scope = rand()%4;

    switch(scope){
        case 0:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        case 1:
           num1 = rand()%100;
           num2 = rand()%50;
           break;
        case 2:
           num1 = rand()%100;
           num2 = rand()%75;
           break;
        case 3:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
        default:
           num1 = rand()%100;
           num2 = rand()%100;
           break;
    }


    int ans = num1 * num2;

    stringstream equationStr;
    equationStr<<ans<<" / "<<num2<<" "<<num1;

    string eqn = equationStr.str();
    f<<eqn<<endl;

    f.close();

}

