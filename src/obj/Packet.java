package obj;

import utils.PacketReader;

import java.io.*;

public class Packet {
    
    private Header HEADER;
    private Question QUESTION;
    private Answer ANSWERS;
    
	public Packet(Header header, Question question2) {
		
		this.setHeader(header);
		this.setQuestion(question2);
	}

    public Packet(Header header, Question question2, Answer answer) {

        this.setHeader(header);
        this.setQuestion(question2);
        this.setAnswer(answer);
    }

	public Packet (byte [] data) throws IOException{
		PacketReader.getPacketReader().setData(data);
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        DataInputStream dis = new DataInputStream (bis);
        this.setHeader(new Header(dis));
        
        //set question
        int questionCount = this.getHeader().getQDCount();
        if(questionCount > 0){
            this.setQuestion (new Question(dis));
        }
        
        //set answer
        int answerCount = this.getHeader ().getANCount ();
        if(answerCount > 0){
            this.setAnswer (new Answer(answerCount, dis));
        }
        
	}
	
    public void setHeader (Header header) {
        this.HEADER = header;
    }

    public Header getHeader () {
        return this.HEADER;
    }

    public void setQuestion (Question question2) {
        this.QUESTION = question2;
    }

    public Question getQuestion () {
        return this.QUESTION;
    }

    public void setAnswer (Answer answer) {
        this.ANSWERS = answer;
    }

    public Answer getAnswer () {
        return this.ANSWERS;
    }

    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.write(this.getHeader() == null ? "".getBytes():this.getHeader().toByteArray());
        dos.write(this.getQuestion() == null ? "".getBytes():this.getQuestion().toByteArray());
        dos.write(this.getAnswer() == null ? "".getBytes():this.getAnswer().toByteArray());
        
        dos.flush();
        return bos.toByteArray();
    }    
    
    public String toString() {
    	
        StringBuilder sb = new StringBuilder ();
        sb.append ("Header:\n");
        sb.append("-------------------------\n");
        sb.append (this.getHeader().toString());
        sb.append("\n\n");
        sb.append ("Question:\n");
        sb.append("-------------------------\n");
        sb.append (this.getQuestion().toString());
        sb.append("\n\n");
        sb.append ("Answer:\n");
        sb.append("-------------------------\n");
        sb.append (this.getAnswer());
        sb.append("\n\n");
        
        return sb.toString();
    }
}
