import java.io.*;
import java.util.*;

public class Menu {
    Scanner scan = new Scanner(System.in);
    ArrayList<Persona> partecipanti = new ArrayList();
    ArrayList<Persona> copia = new ArrayList();
    ArrayList<Ruolo> personaggi = new ArrayList();
    ArrayList<Argomento> temi = new ArrayList();
    HashMap<String, String> associazione = new HashMap();

    //MAIN
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.menu_principale();
    }

    //MENU PRINCIPALE
    public void menu_principale() throws IOException {
        int liv0_sel1 = 0;
        boolean ok;

        do {
            clearScreen();
            System.out.println("Menu Principale");
            System.out.println("+-----------------------------------------------+");
            System.out.println("[1] PARTECIPANTI    Apre il Menu dei Partecipanti");
            System.out.println("[2] MODALITA'       Apre il Menu delle Modalita'");
            System.out.println("[0] ESCI            Chiude il Programma");
            System.out.println("+-----------------------------------------------+");

            do {
                try {
                    System.out.print("Selezione: ");
                    liv0_sel1 = scan.nextInt();
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("Non è un numero!");
                    ok = false;
                    scan.nextLine();
                }
            } while (!ok);

            if (liv0_sel1 < 0 || liv0_sel1 > 2) {
                System.out.println("Scelta sbagliata, riprova.");
            } else {
                switch (liv0_sel1) {
                    case 1:
                        menu_partecipanti();
                        break;

                    case 2:
                        menu_modalita();
                        break;
                }
            }

            if (liv0_sel1 != 0) {
                promptEnterKey();
            }
        } while (liv0_sel1 != 0);
        System.out.println("Alla prossima!");
    }

    //MENU PARTECIPANTI
    public void menu_partecipanti() {
        int liv1_sel1 = 0;
        boolean ok;
        boolean trovato;
        String nome;

        do {
            clearScreen();
            System.out.println("Menu Partecipante");
            System.out.println("+------------------------------------------+");
            System.out.println("[1] - AGGIUNGI      Aggiunge un partecipante");
            System.out.println("[2] - ELIMINA       Elimina un partecipante");
            System.out.println("[3] - MISCHIA       Mischia i partecipante");
            System.out.println("[4] - STAMPA        Stampa i partecipanti");
            System.out.println("[0] - INDIETRO      Torna al menu precedente");
            System.out.println("+------------------------------------------+");

            do {
                try {
                    System.out.print("Selezione: ");
                    liv1_sel1 = scan.nextInt();
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("Non è un numero!");
                    ok = false;
                    scan.nextLine();
                }
            } while (!ok);

            if (liv1_sel1 < 0 || liv1_sel1 > 4) {
                System.out.println("Scelta sbagliata, riprova.");
            } else {
                switch (liv1_sel1) {
                    case 1:
                        scan = new Scanner(System.in);

                        do {
                            System.out.print("Inserisci nome: ");
                            nome = scan.nextLine();
                            if (!"".equals(nome)){
                                if (partecipanti.isEmpty()) {
                                    partecipanti.add(new Persona(nome));
                                } else {
                                    trovato = false;
                                    for (int i = 0; i < partecipanti.size(); i++) {
                                        if (nome.equals(partecipanti.get(i).getNome())) {
                                            trovato = true;
                                        }
                                    }
                                    if (trovato) {
                                        System.out.print(nome + " è già stato inserito");
                                    } else {
                                        if (!"".equals(nome)) {
                                            partecipanti.add(new Persona(nome));
                                        }
                                    }
                                }
                            }
                        } while (!"".equals(nome));

                        System.out.println("I partecipanti inseriti fino ad ora sono: ");
                        for (int i = 0; i < partecipanti.size(); i++) {
                            copia.add(new Persona(partecipanti.get(i).getNome()));
                            if (i != partecipanti.size() - 1) {
                                System.out.print(partecipanti.get(i).toString() + ", ");
                            } else {
                                System.out.print(partecipanti.get(i).toString() + ".");
                            }
                        }
                        System.out.println("");
                        break;

                    case 2:
                        scan = new Scanner(System.in);
                        System.out.print("Inserisci nome: ");
                        nome = scan.nextLine();
                        Persona pDel = null;
                        for (Persona p : partecipanti) {
                            if (p.getNome().equals(nome)) {
                                pDel = p;
                            }
                            if (!associazione.containsKey(nome)) {
                                associazione.remove(nome);
                            }
                        }
                        if (pDel != null) {
                            partecipanti.remove(pDel);
                            System.out.println("Hai eliminato: " + nome);
                            System.out.println("I partecipanti rimanenti sono: ");
                            for (int i = 0; i < partecipanti.size(); i++) {
                                if (i != partecipanti.size() - 1) {
                                    System.out.print(partecipanti.get(i).toString() + ", ");
                                } else {
                                    System.out.print(partecipanti.get(i).toString() + ".");
                                }
                            }
                        } else {
                            System.out.println(nome + " non è presente");
                        }
                        System.out.println("");
                        break;

                    case 3:
                        Collections.shuffle(partecipanti);
                        System.out.println("I partecipanti sono stai mescolati!");
                        break;

                    case 4:
                        if (!partecipanti.isEmpty()){
                            System.out.println(partecipanti);
                            menu_stampa_partecipanti();
                        } else {
                            System.out.println("Non sono stati inseriti partecipanti!");
                        }
                        break;
                }
            }
            if (liv1_sel1 != 0) {
                promptEnterKey();
            }
        } while (liv1_sel1 != 0);
    }

    //MENU STAMPA PARTECIPANTI
    public void menu_stampa_partecipanti() {
        ArrayList<String> gruppi;
        int liv1_sel1_opt4 = 0;
        boolean ok;
        String elementi;

        do {
            clearScreen();
            System.out.println("Menu Personaggi");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("[1] - SINGOLI       Stampa i partecipanti in colonna");
            System.out.println("[2] - COPPIE        Stampa in partecipanti a gruppi di due");
            System.out.println("[3] - TRIPLETTE     Stampa in partecipanti a gruppi di tre");
            System.out.println("[0] - INDIETRO      Torna al menu precedente");
            System.out.println("+--------------------------------------------------------+");

            do {
                try {
                    System.out.print("Selezione: ");
                    liv1_sel1_opt4 = scan.nextInt();
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("Non è un numero!");
                    ok = false;
                    scan.nextLine();
                }
            } while (!ok);

            if (liv1_sel1_opt4 != 0) {
                if (liv1_sel1_opt4 < 0 || liv1_sel1_opt4 > 3) {
                    System.out.println("Scelta sbagliata, riprova.");
                } else {
                    gruppi = new ArrayList();
                    elementi = "";
                    for (int i = 0; i < partecipanti.size(); i++) {
                        if ((i + 1) % liv1_sel1_opt4 != 0) {
                            elementi += partecipanti.get(i).getNome() + ", ";
                        } else {
                            elementi += partecipanti.get(i).getNome() + ".";
                            gruppi.add(elementi);
                            elementi = "";
                        }
                    }
                    if (liv1_sel1_opt4 != 1) {
                        for (int i = 0; i < partecipanti.size() % liv1_sel1_opt4; i++) {
                            if (i != partecipanti.size() % liv1_sel1_opt4 - 1) {
                                elementi += "mancante, ";
                            } else {
                                elementi += "mancante.";
                            }
                        }
                        gruppi.add(elementi);
                    }
                    for (int i = 0; i < gruppi.size(); i++) {
                        System.out.println(gruppi.get(i));
                    }
                }
            }

            if (liv1_sel1_opt4 != 0) {
                promptEnterKey();
            }
        } while (liv1_sel1_opt4 != 0);
    }

    //MENU MODALITA'
    public void menu_modalita() throws IOException {
        int liv1_sel2 = 0;
        boolean ok;

        do {
            clearScreen();
            System.out.println("Menu Principale");
            System.out.println("+-----------------------------------------------+");
            System.out.println("[1] PERSONAGGI      Apre il Menu dei Personaggi");
            System.out.println("[2] TEMI            Apre il Menu dei Temi");
            System.out.println("[0] INDIETRO        Torna al menu precedente");
            System.out.println("+-----------------------------------------------+");

            do {
                try {
                    System.out.print("Selezione: ");
                    liv1_sel2 = scan.nextInt();
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("Non è un numero!");
                    ok = false;
                    scan.nextLine();
                }
            } while (!ok);
            
            if (liv1_sel2 < 0 || liv1_sel2 > 2) {
                System.out.println("Scelta sbagliata, riprova.");
            } else {
                switch (liv1_sel2) {
                    case 1:
                        menu_personaggi();
                        break;
                    case 2:
                        menu_temi();
                        break;
                 }
            }
            if (liv1_sel2 != 0) {
                promptEnterKey();
            }           
        } while (liv1_sel2 != 0);
    }
    
    //MENU PERSONAGGI
    public void menu_personaggi() throws IOException {
        int liv1_sel2_opt1 = 0;
        boolean ok;
        String conferma = null;
        String nuovo;
        String riga;
        Random rand = new Random();

        do {
            clearScreen();
            System.out.println("Menu Personaggi");
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("[1] - ASSEGNA       Assegna un personaggio ad ogni partecipante");
            System.out.println("[2] - ELIMINA       Elimina un personaggio associato ad un partecipante");
            System.out.println("[3] - STAMPA        Stampa i personaggi associati ai partecipanti");
            System.out.println("[0] - INDIETRO      Torna al menu precedente");
            System.out.println("+---------------------------------------------------------------------+");

            do {
                try {
                    System.out.print("Selezione: ");
                    liv1_sel2_opt1 = scan.nextInt();
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("Non è un numero!");
                    ok = false;
                    scan.nextLine();
                }
            } while (!ok);

            if (liv1_sel2_opt1 < 0 || liv1_sel2_opt1 > 3) {
                System.out.println("Scelta sbagliata, riprova.");
            } else {
                switch (liv1_sel2_opt1) {
                    case 1:
                        if (partecipanti.isEmpty()) {
                            System.out.println("Non è stato inserito nessun partecipante");
                            break;
                        } else {
                            try (FileReader file = new FileReader("I:\\Menu\\files_lettura\\Personaggi.txt")) {
                                BufferedReader lettore = new BufferedReader(file);
                                if (!associazione.isEmpty()) {
                                    associazione.clear();
                                    personaggi.clear();
                                }
                                riga = "";
                                while (riga != null) {
                                    riga = lettore.readLine();
                                    if (riga != null) {
                                        personaggi.add(new Ruolo(riga));
                                    }
                                }
                                file.close();
                                if (partecipanti.size() > personaggi.size()) {
                                    System.out.println("I partecipanti sono " + (partecipanti.size() - personaggi.size()) + " in più rispetto ai personaggi");
                                } else {

                                    Collections.sort(personaggi, (Ruolo r1, Ruolo r2) -> r1.getNome().compareToIgnoreCase(r2.getNome()));

                                    int grandezza = personaggi.size();
                                    for (int i = 1; i < grandezza; i++) {
                                        if (personaggi.get(i).getNome().toUpperCase().equals(personaggi.get(i - 1).getNome().toUpperCase())) {
                                            System.out.println(personaggi.get(i).getNome() + " è presente più di una volta");
                                            personaggi.remove(i);
                                            grandezza--;
                                        }
                                    }
                                    
                                    Collections.shuffle(personaggi);
                                   
                                    for (int i = 0; i < partecipanti.size(); i++) {
                                        associazione.put(partecipanti.get(i).getNome(), personaggi.get(i).getNome());
                                        personaggi.remove(i);
                                    }
                                    System.out.println("I personaggi sono stati assegnati");
                                }
                            } catch (FileNotFoundException ex) {
                                System.out.println("File non trovato");
                            }
                        }
                        break;

                    case 2:
                        if (partecipanti.isEmpty()) {
                            System.out.println("Non c'è nessun partecipante!");
                        } else {
                            scan = new Scanner(System.in);
                            System.out.print("Inserisci nome: ");
                            String nDel = scan.nextLine();
                            if (associazione.containsKey(nDel)) {
                                nuovo = nDel;
                                associazione.remove(nDel);

                                do {
                                    if (!personaggi.isEmpty()) {
                                        System.out.print(nDel + " è stato rimosso \nAssegnare un altro personaggio? [S/N]: ");
                                        conferma = scan.nextLine();
                                        if ("S".equals(conferma)) {
                                            Collections.shuffle(personaggi);
                                            associazione.put(nuovo, personaggi.get(rand.nextInt(personaggi.size())).getNome());
                                            System.out.println("Il personaggio è stato assegnato");
                                        } else {
                                            if ("N".equals(conferma)) {
                                                break;
                                            } else {
                                                System.out.println("Risposta non valida");
                                            }
                                        }
                                    } else {
                                        System.out.println("Personaggi terminati!");
                                    }
                                } while (!"S".equals(conferma) && !"N".equals(conferma));

                            } else {
                                System.out.println("Persona non trovata");
                            }
                        }
                        break;

                    case 3:
                        if (partecipanti.isEmpty()) {
                            System.out.println("Non c'è nessun partecipante!");
                        } else {
                            Set set = associazione.entrySet();
                            Iterator iterator = set.iterator();
                            while (iterator.hasNext()) {
                                Map.Entry mentry = (Map.Entry) iterator.next();
                                System.out.println(mentry.getKey() + " è " + mentry.getValue());
                            }
                        }
                        break;

                }
            }

            if (liv1_sel2_opt1 != 0) {
                promptEnterKey();
            }
        } while (liv1_sel2_opt1 != 0);
    }

    //MENU TEMI
    public void menu_temi() throws IOException {
        int liv1_sel2_opt2 = 0;
        boolean ok;
        String riga;
        String conferma;
        Random rand = new Random();

        do {
            clearScreen();
            System.out.println("Menu Temi");
            System.out.println("+----------------------------------------------+");
            System.out.println("[1] - STAMPA		Stampa un tema");
            System.out.println("[0] - INDIETRO		Torna al Menu Precedente");
            System.out.println("+----------------------------------------------+");

            do {
                try {
                    System.out.print("Selezione: ");
                    liv1_sel2_opt2 = scan.nextInt();
                    ok = true;
                } catch (Exception ex) {
                    System.out.println("Non è un numero!");
                    ok = false;
                    scan.nextLine();
                }
            } while (!ok);
            if (liv1_sel2_opt2 < 0 || liv1_sel2_opt2 > 1) {
                System.out.println("Scelta sbagliata, riprova.");
            } else {
                switch (liv1_sel2_opt2) {
                    case 1:
                        try (FileReader file = new FileReader("I:\\Menu\\files_lettura\\Argomenti.txt")) {
                            BufferedReader lettore = new BufferedReader(file);
                            riga = "";
                            while (riga != null) {
                                riga = lettore.readLine();
                                if (riga != null) {
                                    temi.add(new Argomento(riga));
                                }
                            }

                            Collections.sort(temi, (Argomento a1, Argomento a2) -> a1.getNome().compareToIgnoreCase(a2.getNome()));

                            int grandezza = temi.size();
                            for (int i = 1; i < grandezza; i++) {
                                if (temi.get(i).getNome().toUpperCase().equals(temi.get(i-1).getNome().toUpperCase())) {                                    
                                    System.out.println(temi.get(i).getNome() + " è presente più di una volta");
                                    temi.remove(i);
                                    grandezza--;
                                }
                            }
                            
                            Collections.shuffle(temi);
                            
                            int i = 0;
                            System.out.println("Il tema è: " + temi.get(i).getNome());
                            temi.remove(i);
                            
                            do { 
                                scan = new Scanner(System.in);
                                System.out.print("Stampare un altro tema? [S/N]: ");
                                conferma = scan.nextLine();
                                if ("S".equals(conferma)) {
                                    if (temi.isEmpty()) {
                                        System.out.println("I temi sono terminati!");
                                    } else {
                                        i = rand.nextInt(temi.size() - 1);
                                        System.out.println("Il tema è: " + temi.get(i).getNome());
                                        temi.remove(i);
                                    }
                                } else {
                                    if ("N".equals(conferma)) {
                                        System.out.println("Uscita...");
                                    } else {
                                        System.out.println("Scelta sbagliata! Riprova!");
                                    }
                                }
                            } while (!"S".equals(conferma) && !"N".equals(conferma));     
                        } catch (FileNotFoundException ex) {
                            System.out.println("File non trovato");
                        }
                        break;
                }
            }

            if (liv1_sel2_opt2 != 0) {
                promptEnterKey();
            }

        } while (liv1_sel2_opt2 != 0);
    }
    
    //MENU TEMPISTICHE

    //PAUSA
    public void promptEnterKey() {
        System.out.print("Premi \"INVIO\" per continuare...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //PILISCI SCHERMO (NON FUNZIONA)
    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
