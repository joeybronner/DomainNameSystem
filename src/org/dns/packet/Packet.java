package org.dns.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.dns.packet.abstraction.IPacket;
import org.dns.packet.section.*;
import org.dns.util.PacketReader;
import org.dns.packet.abstraction.IAdditional;
import org.dns.packet.abstraction.IAnswer;
import org.dns.packet.abstraction.IAuthority;
import org.dns.packet.abstraction.IHeader;
import org.dns.packet.abstraction.IQuestion;

public class Packet implements IPacket {
    
    private IHeader HEADER;
    private IQuestion QUESTION;
    private IAnswer ANSWERS;
    private IAuthority AUTHORITY;
    private IAdditional ADDITIONAL;
    
	public Packet(IHeader header, IQuestion question, IAnswer answer,
			IAuthority authority, IAdditional additional) {
		
		this.setHeader(header);
		this.setQuestion(question);
		this.setAnswer(answer);
		this.setAuthority(authority);
		this.setAdditional(additional);
		
	}
		
	public Packet (byte [] data) throws IOException{
		PacketReader.getPacketReader().setData(data);
        ByteArrayInputStream bis = new ByteArrayInputStream (data);
        DataInputStream dis = new DataInputStream (bis);
        this.setHeader (new Header(dis));
        
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
        
        //set authority 
        int nscount = this.getHeader().getNSCount();
        if(nscount > 0){
            this.setAuthority (new Authority(nscount, dis));
        }
        
        //set additional
        int arcount = this.getHeader().getARCount();
        if(arcount > 0){
            this.setAdditional (new Additional(arcount, dis));
        }
	}
    
    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#setHeader(org.dns.interfaces.org.dns.packet.IHeader)
     */
    @Override
    public void setHeader (IHeader header) {
        this.HEADER = header;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#getHeader()
     */
    @Override
    public IHeader getHeader () {
        return this.HEADER;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#setQuestion(org.dns.interfaces.org.dns.packet.IQuestion)
     */
    @Override
    public void setQuestion (IQuestion question) {
        this.QUESTION = question;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#getQuestion()
     */
    @Override
    public IQuestion getQuestion () {
        return this.QUESTION;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#setAnswer(org.dns.interfaces.org.dns.packet.IAnswer)
     */
    @Override
    public void setAnswer (IAnswer answer) {
        this.ANSWERS = answer;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#getAnswer()
     */
    @Override
    public IAnswer getAnswer () {
        return this.ANSWERS;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#setAuthority(org.dns.interfaces.org.dns.packet.IAuthority)
     */
    @Override
    public void setAuthority (IAuthority authority) {
        this.AUTHORITY = authority;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#getAuthority()
     */
    @Override
    public IAuthority getAuthority () {
        return this.AUTHORITY;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#setAdditional(org.dns.interfaces.org.dns.packet.IAdditional)
     */
    @Override
    public void setAdditional (IAdditional additional) {
        this.ADDITIONAL = additional;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#getAdditional()
     */
    @Override
    public IAdditional getAdditional () {
        return this.ADDITIONAL;
    }

    /* (non-Javadoc)
     * @see org.dns.interfaces.org.dns.packet.IPacket#toByteArray()
     */
    @Override
    public byte[] toByteArray () throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.write(this.getHeader() == null ? "".getBytes():this.getHeader().toByteArray());
        dos.write(this.getQuestion() == null ? "".getBytes():this.getQuestion().toByteArray());
        dos.write(this.getAnswer() == null ? "".getBytes():this.getAnswer().toByteArray());
        dos.write(this.getAuthority() == null ? "".getBytes():this.getAnswer().toByteArray());
        dos.write(this.getAdditional() == null ? "".getBytes():this.getAnswer().toByteArray());
        
        dos.flush();
        return bos.toByteArray();
    }    
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder ();
        sb.append ("Header:\n");
        sb.append("-------------------------\n");
        sb.append (this.getHeader ().toString());
        sb.append("\n\n");
        sb.append ("Question:\n");
        sb.append("-------------------------\n");
        sb.append (this.getQuestion ().toString());
        sb.append("\n\n");
        sb.append ("Answer:\n");
        sb.append("-------------------------\n");
        sb.append (this.getAnswer ());
        sb.append("\n\n");
        sb.append ("Authority:\n");
        sb.append("-------------------------\n");
        sb.append (this.getAuthority());
        sb.append("\n\n");sb.append ("Additional:\n");
        sb.append("-------------------------\n");
        sb.append (this.getAdditional());
        return sb.toString ();
    }
}
