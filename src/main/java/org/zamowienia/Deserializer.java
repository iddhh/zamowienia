package org.zamowienia;

import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class Deserializer {
    public void deserializing(Map<String,String> jsonlist) {
        ObjectMapper mapper = new ObjectMapper();
        for (Map.Entry<String, String> jsonmap : jsonlist.entrySet()) {
            String filename = jsonmap.getKey();
            String json = jsonmap.getValue();
            try {
                Dane dane = mapper.readValue(json, Dane.class);

                System.out.println("########################");
                System.out.println("Zamowienie nr: "+dane.getId());
                System.out.println("Filename:"+filename);
                System.out.println("Imię:"+dane.getImie());
                System.out.println("Nazwisko:"+dane.getNazwisko());
                System.out.println("E-mail:"+dane.getEmail());
                System.out.println("Numer telefonu:"+dane.getNumerTelefonu());

                System.out.println("Produkty:");

                for (Produkt produkt : dane.getProdukty()) {
                    System.out.println("Kod produktu:"+produkt.getKodProduktu());
                    System.out.println("Ilość:"+produkt.getIlosc());
                    System.out.println("Jednostka miary:"+produkt.getJednostkaMiary());
                }
                System.out.println("########################");
                System.out.println(" ");

            } catch (Exception e) {
                System.err.println("Bład w deserializacji");
            }
        }
    }

    public static class Dane {
        private String imie;
        private String nazwisko;
        private String email;
        private String numerTelefonu;
        private List<Produkt> produkty;

        public Dane() {
        }
        public Integer getId() {return abs(imie.chars().sum()*nazwisko.chars().sum()*email.chars().sum()*numerTelefonu.chars().sum());}

        public String getImie() {
            return imie;
        }

        public String getNazwisko() {
            return nazwisko;
        }

        public String getEmail() {
            return email;
        }

        public String getNumerTelefonu() {
            return numerTelefonu;
        }

        public List<Produkt> getProdukty() {
            return produkty;
        }
    }

    public static class Produkt {
        private int kodProduktu;
        private int ilosc;
        private String jednostkaMiary;

        public Produkt() {
        }

        public int getKodProduktu() {
            return kodProduktu;
        }

        public int getIlosc() {
            return ilosc;
        }

        public String getJednostkaMiary() {
            return jednostkaMiary;
        }
    }
}


