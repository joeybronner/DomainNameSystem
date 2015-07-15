package server;


import obj.Answer;
import obj.Packet;
import obj.Record;
import utils.enums.QClass;
import utils.enums.QType;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;


public class DNSServer {
    public static void main(String[] args) {
        HashMap<String, Record[]> domaines = new HashMap<String, Record[]>();
        initDomaines(domaines);
        DatagramSocket server = null;
        byte[] buf = new byte[1024];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        try {
            server = new DatagramSocket(53);
            while (true) {
                try {
                    server.receive(p);
                    Packet pac = new Packet(p.getData());
                    if (domaines.containsKey(pac.getQuestion().getQName())) {
                        Packet responsePacket = new Packet(pac.getHeader(), pac.getQuestion(), new Answer(domaines.get(pac.getQuestion().getQName())));

                        // Retourne la response
                        InetAddress address = p.getAddress();
                        int port = p.getPort();
                        DatagramPacket dnsPacket = new DatagramPacket(responsePacket.toByteArray(), responsePacket.toByteArray().length, address, port);
                        server.send(dnsPacket);
                    }
                    else {
                        // Pas de domaine, google
                        Packet dnspacket = new Packet(pac.getHeader(), pac.getQuestion());
                        byte [] SERVER_ADDRESS = {8,8,8,8};
                        byte[] data = dnspacket.toByteArray();
                        DatagramSocket socket = new DatagramSocket();
                        DatagramPacket packet = new DatagramPacket (
                                data,
                                data.length,
                                InetAddress.getByAddress(SERVER_ADDRESS),
                                53);
                        socket.setSoTimeout(5000);
                        socket.send(packet);


                        packet = new DatagramPacket(new byte [512], 512);
                        socket.receive(packet);
                        byte[] arr = packet.getData();

                        // Retourne la response
                        InetAddress address = p.getAddress();
                        int port = p.getPort();
                        packet = new DatagramPacket(packet.getData(), packet.getData().length, address, port);
                        server.send(packet);
                        System.out.println("toto");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }

    private static void initDomaines(HashMap<String, Record[]> domaines) {
        // int ttl,  int rdlength, String rdata
        Record[] r = {new Record("google.fr.", QType.A, QClass.IN, 256, 4, "??(?"), new Record("google.fr", QType.A, QClass.IN, 256, 4, "??(?"),
                new Record("google.fr", QType.A, QClass.IN, 256, 4, "??(?"), new Record("google.fr", QType.A, QClass.IN, 256, 4, "??(?")};
        domaines.put("google.fr.", r);
        r = new Record[]{new Record("wehicles.com.", QType.A, QClass.IN, 3050, 4, "??(?")};
        domaines.put("wehicles.com.", r);
    }
}
