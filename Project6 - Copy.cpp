// Author: Swrajit Paul

#include <iostream>
#include <fstream>

using namespace std;

ifstream inFile;
ifstream inFiletwo;
ofstream outFile;
ofstream outFiletwo;

class image {
	
	public:
		
		int numRows;
		int numCols; 
		int minVal; 
		int maxVal; 
					
		int** imageAry;
		int** CCAry;
	
	    image(string in, string intwo, string out, string outtwo) {
	    	
	    	inFile.open(in.c_str());
  			
			inFiletwo.open(intwo.c_str());
  			
			outFile.open(out.c_str());
			
			outFiletwo.open(outtwo.c_str());
	    	
			inFile >> numRows;
			inFile >> numCols; 
			inFile >> minVal; 
			inFile >> maxVal;
			
			outFile << numRows;
  			outFile << " ";
  			outFile << numCols;
  			outFile << " ";
  			outFile << minVal;
  			outFile << " "; 
  			outFile << maxVal;
  			outFile << endl;
  			
  			outFiletwo << numRows;
  			outFiletwo << " ";
  			outFiletwo << numCols;
  			outFiletwo << " ";
  			outFiletwo << minVal;
  			outFiletwo << " "; 
  			outFiletwo << maxVal;
  			outFiletwo << endl;
  			
			imageAry = new int*[numRows+2];
			for(int i = 0; i < numRows+2; i++){
				imageAry[i] = new int[numCols+2]; }// set up the array with proper rows and cols
			for(int i = 0; i < numRows+2; i++) {
				for(int j = 0; j < numCols+2; j++) {
					imageAry[i][j] = 0; } }// initialize the array
			
			CCAry = new int*[numRows+2];
			for(int i = 0; i < numRows+2; i++){
				CCAry[i] = new int[numCols+2];
			}// set up the array with proper rows and cols
			for(int i = 0; i < numRows+2; i++) {
				for(int j = 0; j < numCols+2; j++) {
					CCAry[i][j] = 0; } }// initialize the array
	    } 
	    
	    void loadImage(int** FramedAry) {
			// reads line by line from the input into zeroFramedAry
			for(int i = 1; i < numRows+1; i++) {
				for(int j = 1; j < numCols+1; j++) {
					inFile >> FramedAry[i][j]; } }

		}
		
		void zeroFramed(int** FramedAry) {
			for(int j = 0; j < numCols+2; j++) {
				FramedAry[0][j] = 0;
				FramedAry[numRows+1][j] = 0; }
			
			for(int j = 0; j < numRows+2; j++) {
				FramedAry[j][0] = 0;
				FramedAry[j][numCols+1] = 0; }
		}
				
};


class connectCC {
	
	public:
		
		int label;
		int numPixels;
		int minRow;
		int minCol;
		int maxRow;
		int maxCol;
	
	    connectCC(int lab, int np, int minR, int minC, int maxR, int maxC, image im) {
		    label = lab;
			numPixels = np;
			minRow = minR;
			minCol = minC;
			maxRow = maxR;
			maxCol = maxC;
			clearCC(im.CCAry, im.numRows+2, im.numCols+2);
			loadCC(label, im.CCAry, im);
	    } 
	    
	    void clearCC(int** CCAry, int row, int col){
	    	for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					CCAry[i][j] = 0; 
				} 
			}
		}
		
		void loadCC(int ccLable, int** CCAry, image im){
			for(int i = 0; i < im.numRows+2; i++) {
				for(int j = 0; j < im.numCols+2; j++) {
					if(im.imageAry[i][j] == ccLable)
						CCAry[i][j] = im.imageAry[i][j];
				} 
			}
		}
			
};

class chainCode{
	
	struct Point {
		int row;
		int col;
	};
	
	public:	 
		Point neighborCoord[8];
		Point startP;
		Point currentP;
		Point nextP;    
		int lastQ; 
		int nextDirTable[8] = = {6, 0, 0, 2, 2, 4, 4, 6};;
		int nextDir; 
		int PchainDir;
	 
		chainCode(connectCC CC, image im){
			getChainCode(CC, im.CCAry);
		}
		
		void getChainCode(connectCC cc, int** CCAry){
			int minRow = cc.minRow;
			int minCol = cc.minCol;
			int maxRow = cc.maxRow;
			int maxCol = cc.maxCol;
			
			for(int i = minRow; i <= maxRow; i++) {
				for(int j = minCol; j <= maxCol; j++) {
					if(CCAry[i][j] > 0){
						outFile << 3;
	  					outFiletwo << 3;
						startP.row = i;
						startP.col = j;
						currentP.row = i;
						currentP.col = j;	
						lastQ = 4;
						while(true){
							nextQ = (lastQ+1) % 8;
							
							PchainDir = findNextP(currentP, nextQ, nextP);
							
							count++;
							
							prettyPrint(2, count, PchainDir, 0);
							
							if(count == 15){
				      			count = 0;
							}
				      		
							nextQ = PchainDir -1;
				      		
							if(nextQ < 0){
				      			nextQ += 8;	
							}
				      		
							lastQ = nextDirTable[nextQ];
				      		
							currentP.row = nextP.row;
				      		currentP.col = nextP.col;
				      		
				      		if((currentP.row == startP.row) && (currentP.col == startP.col)){
								break;
							}
						}
					}
				} 
			}
			
		}
		void loadNeighborsCoord(Point currentP) {
			
		}
		void findNextP(Point currentP,int nextQ, int nextP){
		 // see algorithm below.
			// begin chain-code construction for the given connected component
		}
		void prettyPrint() {
			
		// you know how to do this
		}
};		 
	
	
int main(int argc, char *argv[]){
	
	image img(argv[1], argv[2], argv[3], argv[4]);
	img.loadImage(img.imageAry);
	img.zeroFramed(img.imageAry);
	
	int temp;
	inFiletwo >> temp;
	inFiletwo >> temp;
	inFiletwo >> temp;
	inFiletwo >> temp;
	// skipping lines 
	
	inFiletwo >> temp;
	
	for(int i=0; i < temp; i++){
		
		int label, numPixels, minRow, minCol, maxRow, maxCol;
		inFiletwo >> label;
		inFiletwo >> numPixels;
		inFiletwo >> minRow;
		inFiletwo >> minCol;
		inFiletwo >> maxRow;
		inFiletwo >> maxCol;
		
		connectCC ConCC(label, numPixels, minRow, minCol, maxRow, maxCol, img);
		chainCode chainC(ConCC, img);
	}
	
	inFile.close();
	inFiletwo.close();
	outFile.close();
	outFiletwo.close();
	return 0;
}
	
