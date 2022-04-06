package me.lyzev.whois;

public class WhoIsExample {

    /**
     * AN example of how to use the library.
     */
    public static void main(String[] args) {
        WhoIs whoIs = new WhoIs("example.com");
        whoIs.doRequest().forEach(System.out::println);
    }
}
