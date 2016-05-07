package dbenson2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {
	
	 private char[] answers;
	 private char[] responses;
	 private final double requiredPct = 0.7;
	 
	 public DriverExam(char[] answers) {
		 super();
		 this.answers = new char[answers.length];
		 for(int i = 0; i < answers.length; i++){
			 this.answers[i] = answers[i];
		 }
		 
	 }

	 public DriverExam(DefaultListModel answers){
		 this.answers = new char[answers.getSize()];
		 for (int i = 0; i < answers.getSize(); i++){
			 String a = (String) answers.get(i);
			 this.answers[i] = a.charAt(0);
		 }
	 }
	 
	 public void setResponses(DefaultListModel responses){
		 this.responses = new char[responses.getSize()];
		 for (int i = 0; i < responses.getSize(); i++){
			 String r = (String) responses.get(i);
			 this.responses[i] = r.charAt(0);
		 }
		 
	 }
	 
	 public DefaultListModel getAnswers(){
		 DefaultListModel answersListModel = new DefaultListModel();
		 
		 for (int i = 0; i < answers.length; i++ ){
			 answersListModel.addElement(Character.toString(answers[i]));
		 }
		 
		 return answersListModel;
	 }
	 
	 public int validate(){
		 
		 int index;
		 int element;
		 
		 
		 element = -1;
		 index = 0;
		 
		 while (element > responses.length){
			 if (responses[index] != 'B' && responses[index] != 'D' && responses[index] != 'C' && responses[index] != 'A' ){
				 element = responses[index]; 
				 
			 }
			 else{
				 index++;
				 
			 }
			
		 }
		 return element;	 
	  }
		
	 
	 
	 public boolean passed(){
		 
		 boolean p;
		 
		 p = false;
		 
		 if (totalCorrect() >= requiredPct * answers.length)
			 p = true;
		 
		 return p;
	 }
	 
	 public int totalCorrect(){
		 
		 int correct = 0;
		 
		 for (int i = 0; i < answers.length; i++ ){
			 if (answers[i] == responses[i])
				 correct++;	 
		 }
		 
		 return correct;
	 }
	 
	 public int totalIncorrect(){
		 
		 
		 int m = 0;
		 
		 for (int i = 0; i < answers.length; i++){
			 if (answers[i] != responses[i]){
			 	 m++;
			 }	
		 }
		 
		 return m;
		 
	 }
	 
	 public int[] questionsMissed(){
		 
		 int[] missed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		 int m = 0;
	 
		 for (int i = 0; i < answers.length; i++){
			 if (answers[i] != responses[i]){
				 missed[m] = i + 1;
				 m++;
			 }	
		 }
	 
		 return missed;
	}
}
