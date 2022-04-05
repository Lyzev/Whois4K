package me.lyzev.whois;

public class WhoIsExample {

    /**
     * AN example of how to use the library.
     */
    public static void main(String[] args) {
        WhoIs whoIs = new WhoIs("google.com");
        whoIs.doRequest().forEach(System.out::println);
    }
}
