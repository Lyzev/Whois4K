package me.lyzev.whois;

public class WhoisExample {

    /**
     * An example of how to use the library.
     */
    public static void main(String[] args) {
        Whois whois = new Whois("example.com");
        whois.doRequest().forEach(System.out::println);
    }

}
