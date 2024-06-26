package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.*;
import Foods.*;
import Rooms.*;
import Amenity.*;
import Transaction.*;

public class Methods{

    private Scanner sc = new Scanner(System.in);        //For string inputs
    private Scanner in = new Scanner(System.in);        //For integer/long/double inputs
    String choice = null;
    int pick = 0;

    //CONSTRUCTOR
    public Methods() {
    }

    //====================================USEFUL METHODS============================================

    public void addAmenity(ArrayList<Amenity> amenities, String amenityType) {
        sortAmenity(amenities);
        int gameNum = 0;
        int karaokeNum = 0;
        int poolNum = 0;
        int hallNum = 0;

        for(int i = 0; i<amenities.size(); i++) {       //Counts the amenity type in the arraylist
            Amenity amenity = amenities.get(i);
            if(amenity.getClass()== GameRoom.class) {gameNum++;}
            else if(amenity.getClass()== Karaoke.class) {karaokeNum++;}
            else if(amenity.getClass()== Pool.class) {poolNum++;}
            else if(amenity.getClass()== ReceptionHall.class) {hallNum++;}
        }

        switch (amenityType) {
            case "GameRoom":
                gameNum++;
                if (gameNum < 10) amenities.add(new GameRoom("G0" + gameNum));
                else amenities.add(new GameRoom("G" + gameNum));
                break;
            case "Karaoke":
                karaokeNum++;
                if (karaokeNum < 10) amenities.add(new Karaoke("K0" + karaokeNum));
                else amenities.add(new Karaoke("K" + karaokeNum));
                break;
            case "Pool":
                poolNum++;
                if (poolNum < 10) amenities.add(new Pool("P0" + poolNum));
                else amenities.add(new Karaoke("P" + karaokeNum));
                break;
            case "ReceptionHall":
                hallNum++;
                if (hallNum < 10) amenities.add(new ReceptionHall("H0" + hallNum));
                else amenities.add(new ReceptionHall("H" + hallNum));
                break;
            default:
                System.out.println("INVALID: Amenity is unknown!");
                break;
        }
    }

    public void displayAmenities(ArrayList<Amenity> amenities) {
        sortAmenity(amenities);
        System.out.println("\n=====AMENITIES-LIST=====");
        for(int i = 0; i<amenities.size(); i++) {
            Amenity amenity = amenities.get(i);
            System.out.print(i+1 + " ");

            if(amenity.getClass()==Karaoke.class) {     //Display the type of amenity
                System.out.print("Karaoke");
            }else if(amenity.getClass()==ReceptionHall.class) {
                System.out.print("Reception Hall");
            }else if(amenity.getClass()==Pool.class) {
                System.out.print("Pool");
            }else if(amenity.getClass()==GameRoom.class){
                System.out.print("Game Room");
            }

            System.out.print(" || Amenity Code: " + amenity.getAmenityCode());
            System.out.println(" || Reservation cost: " + amenity.getReservationCost());
        }
    }

    void sortAmenity(ArrayList<Amenity> amenities) {
        ArrayList<GameRoom> games = new ArrayList<>();
        ArrayList<Karaoke> karaokes = new ArrayList<>();
        ArrayList<Pool> pools = new ArrayList<>();
        ArrayList<ReceptionHall> halls = new ArrayList<>();
        ArrayList<Amenity> allAmenities = new ArrayList<>();      //Store here the sorted obj

        for(int i = 0; i<amenities.size(); i++) {
            Amenity amenity = amenities.get(i);
            if(amenity.getClass()==GameRoom.class) games.add((GameRoom) amenity);
            else if(amenity.getClass()==Karaoke.class) karaokes.add((Karaoke) amenity);
            else if(amenity.getClass()==Pool.class) pools.add((Pool) amenity);
            if(amenity.getClass()==ReceptionHall.class) halls.add((ReceptionHall) amenity);
        }

        amenities.removeAll(amenities);

        //Add here the algo to sort the amenity by number
        for(int i = 0; i< games.size(); i++) {
            GameRoom game = games.get(i);
            String amenityCode = game.getAmenityCode();
            for(int j = i+1; j<games.size(); j++) {
                GameRoom game2 = games.get(j);
                String amenityCode2 = game2.getAmenityCode();
                if(amenityCode.compareTo(amenityCode2)>0) {
                    GameRoom temp = new GameRoom(game);
                    game = new GameRoom(game2);
                    game2 = new GameRoom(temp);
                }
            }
        }

        for(int i = 0; i< karaokes.size(); i++) {
            Karaoke kon = karaokes.get(i);
            String amenityCode = kon.getAmenityCode();
            for(int j = i+1; j<karaokes.size(); j++) {
                Karaoke kon2 = karaokes.get(j);
                String amenityCode2 = kon2.getAmenityCode();
                if(amenityCode.compareTo(amenityCode2)>0) {
                    Karaoke temp = new Karaoke(kon);
                    kon = new Karaoke(kon2);
                    kon2 = new Karaoke(temp);
                }
            }
        }

        for(int i = 0; i< pools.size(); i++) {
            Pool pool = pools.get(i);
            String amenityCode = pool.getAmenityCode();
            for(int j = i+1; j<pools.size(); j++) {
                Pool pool2 = pools.get(j);
                String amenityCode2 = pool2.getAmenityCode();
                if(amenityCode.compareTo(amenityCode2)>0) {
                    Pool temp = new Pool(pool);
                    pool = new Pool(pool2);
                    pool2 = new Pool(temp);
                }
            }
        }

        for(int i = 0; i< halls.size(); i++) {
            ReceptionHall hall = halls.get(i);
            String amenityCode = hall.getAmenityCode();
            for(int j = i+1; j<halls.size(); j++) {
                ReceptionHall hall2 = halls.get(j);
                String amenityCode2 = hall2.getAmenityCode();
                if(amenityCode.compareTo(amenityCode2)>0) {
                    ReceptionHall temp = new ReceptionHall(hall);
                    hall = new ReceptionHall(hall2);
                    hall2 = new ReceptionHall(temp);
                }
            }
        }

        //Add here the algo to add all amenities in allAmenities
        for(Karaoke kon : karaokes) amenities.add(kon);
        for(GameRoom game : games) amenities.add(game);
        for(Pool pool : pools) amenities.add(pool);
        for(ReceptionHall hall : halls) amenities.add(hall);
    }

    public Amenity getAmenity(ArrayList<Amenity> amenities, String amenityCode) {
        Amenity amenity = null;
        for(int i = 0; i<amenities.size(); i++) {
            Amenity amenity1 = amenities.get(i);
            if(amenity1.getAmenityCode().equalsIgnoreCase(amenityCode)) {
                amenity = amenity1;
                break;
            }
        }
        return  amenity;
    }

    public void displayAllRoom(ArrayList<Room> rooms) {     //Display all rooms despite occupied or not
        sortRooms(rooms);
        if(rooms.size()!=0) {       //If there is room existing
            System.out.println("\n===ROOMS-LIST===");
            for (int i = 0; i < rooms.size(); i++) {
                Room room = rooms.get(i);
                System.out.print((i + 1) + ". Room Number: " + room.getRoomNum() + " || Room Type: " + room.getRoomType());
                System.out.print(" || ");
                if(room.getIsOccupied()==true) System.out.print("Occupied ");
                else System.out.print(" Unoccupied ");
                System.out.print(" || ");
                if(room.getIsDisabled()==true) System.out.println("Disabled");
                else System.out.println(" Enabled");
            }
        }else System.out.println("\nThere is no room found\n");
    }

    public ArrayList<Room> getRoomsType(ArrayList<Room> rooms, String roomType) {       //Return an arraylist of Room filter with roomtype
        ArrayList<Room> roomsType = new ArrayList<>();

        for(int i = 0; i<rooms.size(); i++) {
            Room room = rooms.get(i);
            if(room.getRoomType().equals(roomType)==true) roomsType.add(room);
        }

        return roomsType;
    }

    public ArrayList<Amenity> getAmenitiesType(ArrayList<Amenity> amenities, String amenityType) {       //Return an arraylist of Room filter with roomtype
        ArrayList<Amenity> amenityList = new ArrayList<>();

        for(int i = 0; i<amenities.size(); i++) {
            Amenity amenity = amenities.get(i);
            if(amenity.getAmenityType().equals(amenityType)==true) amenityList.add(amenity);
        }

        return amenityList;
    }

    public void setPriceRooms(ArrayList<Room> rooms, double rate) {     //Set the rate of rooms in ArrayList
        for(int i = 0; i<rooms.size();i++) {
            Room room = rooms.get(i);
            room.setRatePerDay(rate);
        }
    }

    public void setRsrvCostAmenities(ArrayList<Amenity> amenities, double rate) {     //Set the reservation cost in ArrayList
        for(int i = 0; i<amenities.size();i++) {
            Amenity amenity = amenities.get(i);
            amenity.setReservationCost(rate);
        }
    }

    public void displayAllRoomForCustomer(ArrayList<Room> rooms) {     //Display all rooms despite occupied or not
        sortRooms(rooms);
        if(rooms.size()!=0) {       //If there is room existing
            System.out.println("\n===ROOMS-LIST===");
            for (int i = 0; i < rooms.size(); i++) {
                Room room = rooms.get(i);
                if(room.getIsOccupied()==false && room.getIsDisabled()==false) {    //Filtering out those unavailable room
                    System.out.println((i + 1) + ". Room Number: " + room.getRoomNum() + " || Room Type: " + room.getRoomType()
                    + " || Cost: " + room.getRatePerDay());
                }
            }
        }else System.out.println("\nThere is no room found\n");
    }

    public ArrayList<Integer> getAvailRoomNum(ArrayList<Room> rooms) {  //Returns a list of roomNumber existed
        ArrayList<Integer> roomNums = new ArrayList<>();
        sortRoomByNum(rooms);
        if(rooms.size()!=0) {
            for (int i = 0; i < rooms.size(); i++) {
                roomNums.add(rooms.get(i).getRoomNum());
            }
        }
        return roomNums;
    }

    public Room getRoomWithRoomNumber(ArrayList<Room> rooms, int roomNum) {
        Room thisRoom = null;

        for(int i = 0; i<rooms.size(); i++) {
            Room room = rooms.get(i);
            if(room.getRoomNum()==roomNum) {
                thisRoom = room;
                break;
            }
        }

        return thisRoom;
    }

    public void displayAvailRoomNum(ArrayList<Room> rooms) {       //Display the list of room number
        sortRoomByNum(rooms);
        ArrayList<Integer> roomNums = getAvailRoomNum(rooms);
        if(roomNums.size()!=0) {
            for(int i = 0; i<roomNums.size(); i++) {
                System.out.print(roomNums.get(i));
                if(i<rooms.size()-1) System.out.print(" | ");;
            }
            System.out.println("");
        }
    }

    public boolean isRoomNumExist(ArrayList<Room> rooms, int roomNum) {     //Returns a boolean if room exist
        boolean isExist = false;
        for(int i = 0; i< rooms.size(); i++) {
            if(rooms.get(i).getRoomNum()==roomNum) isExist = true;
        }
        return isExist;
    }

    public int isRoomNumReturnIndex(ArrayList<Room> rooms, int roomNum) {   //Returns the index of room with room number specified
        int index = -1;
        for(int i = 0; i< rooms.size(); i++) {
            if(rooms.get(i).getRoomNum()==roomNum) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void displayRoomCategory(ArrayList<Room> rooms) {    //Display all room in category
        ArrayList<SingleRoom> snRooms = new ArrayList<SingleRoom>();
        ArrayList<CoupleRoom> cpRooms = new ArrayList<CoupleRoom>();
        ArrayList<FamilyRoom> fmRooms = new ArrayList<FamilyRoom>();
        ArrayList<VIPRoom> vpRooms = new ArrayList<VIPRoom>();

        sortRooms(rooms);

        for (int i = 0; i< rooms.size(); i++) {     // To separate each subclasses
            if(rooms.get(i).getRoomType().equals("Single")==true) {snRooms.add((SingleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Couple")==true) {cpRooms.add((CoupleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Family")==true) fmRooms.add((FamilyRoom) rooms.get(i));
            else if(rooms.get(i).getRoomType().equals("VIP")==true) vpRooms.add((VIPRoom) rooms.get(i));
        }

        boolean isCategory = true;
        while(isCategory==true) {
            if (rooms.size() != 0) System.out.println("\n===ROOMS-IN-CATEGORY===");
            else {
                System.out.println("\nThere is no room found!\n");
                return;
            }

            System.out.println("[1]Single room");
            System.out.println("[2]Couple room");
            System.out.println("[3]Family room");
            System.out.println("[4]VIP room");
            System.out.println("[0]Back");
            System.out.print("Enter choice: ");
            pick = inputInt();

            switch (pick) {
                case 1:     //Single room
                    if (snRooms.size() != 0) {
                        System.out.println("\n===SINGLE-ROOMS===");
                        for (SingleRoom snRoom : snRooms) {
                            System.out.print("Room Number: " + snRoom.getRoomNum());
                            System.out.print(" || ");
                            if(snRoom.getIsOccupied()==true) System.out.print("Occupied ");
                            else System.out.print(" Unoccupied ");
                            System.out.print(" || ");
                            if(snRoom.getIsDisabled()==true) System.out.println("Disabled");
                            else System.out.println(" Enabled");
                        }
                        isGoBack();
                    } else {
                        System.out.println("\nThere is no single room");
                        continue;
                    }
                    break;
                case 2:     //couple room
                    if (cpRooms.size() != 0) {
                        System.out.println("\n===COUPLE-ROOMS===");
                        for (CoupleRoom cpRoom : cpRooms) {
                            System.out.print("Room Number: " + cpRoom.getRoomNum());
                            System.out.print(" || ");
                            if(cpRoom.getIsOccupied()==true) System.out.print("Occupied ");
                            else System.out.print(" Unoccupied ");
                            System.out.print(" || ");
                            if(cpRoom.getIsDisabled()==true) System.out.println("Disabled");
                            else System.out.println(" Enabled");
                        }
                        isGoBack();
                    }else {
                        System.out.println("\nThere is no couple room");
                        continue;
                    }
                    break;
                case 3:     //Family room
                    if (fmRooms.size() != 0) {
                        System.out.println("\n===FAMILY-ROOMS===");
                        for (FamilyRoom fmRoom : fmRooms) {
                            System.out.print("Room Number: " + fmRoom.getRoomNum());
                            System.out.print(" || ");
                            if(fmRoom.getIsOccupied()==true) System.out.print("Occupied ");
                            else System.out.print(" Unoccupied ");
                            System.out.print(" || ");
                            if(fmRoom.getIsDisabled()==true) System.out.println("Disabled");
                            else System.out.println(" Enabled");
                        }
                        isGoBack();
                    }else {
                        System.out.println("\nThere is no family rooms");
                        continue;
                    }

                    break;
                case 4:     //vip room
                    if (vpRooms.size() != 0) {
                        System.out.println("\n===VIP-ROOMS===");
                        for (VIPRoom vpRoom : vpRooms) {
                            System.out.print("Room Number: " + vpRoom.getRoomNum());
                            System.out.print(" || ");
                            if(vpRoom.getIsOccupied()==true) System.out.print("Occupied ");
                            else System.out.print(" Unoccupied ");
                            System.out.print(" || ");
                            if(vpRoom.getIsDisabled()==true) System.out.println("Disabled");
                            else System.out.println(" Enabled");
                        }
                        isGoBack();
                    }else {
                        System.out.println("\nThere is no VIP rooms");
                        continue;
                    }
                    break;
                case 0:
                    isCategory = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    public Room displaySelectRoomCategory(ArrayList<Room> rooms) {    //Display all room in category
        ArrayList<SingleRoom> snRooms = new ArrayList<SingleRoom>();
        ArrayList<CoupleRoom> cpRooms = new ArrayList<CoupleRoom>();
        ArrayList<FamilyRoom> fmRooms = new ArrayList<FamilyRoom>();
        ArrayList<VIPRoom> vpRooms = new ArrayList<VIPRoom>();
        Room room = null;

        sortRooms(rooms);

        for (int i = 0; i< rooms.size(); i++) {     // To separate each subclasses
            Room room1 = rooms.get(i);
            if(room1.getIsOccupied()==true) continue;
            if(room1.getIsDisabled()==true) continue;
            if(room1.getRoomType().equals("Single")==true) {snRooms.add((SingleRoom) room1);}
            else if(room1.getRoomType().equals("Couple")==true) {cpRooms.add((CoupleRoom) room1);}
            else if(room1.getRoomType().equals("Family")==true) fmRooms.add((FamilyRoom) room1);
            else if(room1.getRoomType().equals("VIP")==true) vpRooms.add((VIPRoom) room1);
        }

        boolean isCategory = true;
        while(isCategory==true) {
            if (rooms.size() != 0) System.out.println("\n===ROOMS-IN-CATEGORY===");
            else {
                System.out.println("\nThere is no room found!\n");
                return room;
            }

            System.out.println("[1]Single room");
            System.out.println("[2]Couple room");
            System.out.println("[3]Family room");
            System.out.println("[4]VIP room");
            System.out.println("[0]Back");
            System.out.print("Enter choice: ");
            pick = inputInt();

            switch (pick) {
                case 1:     //Single room
                    if (snRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===SINGLE-ROOMS===");
                            for (SingleRoom snRoom : snRooms) {
                                System.out.println("Room Number: " + snRoom.getRoomNum() + " || Room cost: " + snRoom.getRatePerDay());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(SingleRoom snRoom : snRooms) {      //Find the room with room number inputted
                                if(snRoom.getRoomNum()==pick) {
                                    room=snRoom;
                                    isFound = true;
                                    return snRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else break;
                            }
                        }
                    } else {
                        System.out.println("\nThere is no single room");
                        continue;
                    }
                    break;
                case 2:     //couple room
                    if (cpRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===COUPLE-ROOMS===");
                            for (CoupleRoom cpRoom : cpRooms) {
                                System.out.println("Room Number: " + cpRoom.getRoomNum() + " || Room cost: " + cpRoom.getRatePerDay());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(CoupleRoom cpRoom : cpRooms) {      //Find the room with room number inputted
                                if(cpRoom.getRoomNum()==pick) {
                                    room=cpRoom;
                                    isFound = true;
                                    return cpRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else break;
                            }
                        }
                    }else {
                        System.out.println("\nThere is no couple room");
                        continue;
                    }
                    break;
                case 3:     //Family room
                    if (fmRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===FAMILY-ROOMS===");
                            for (FamilyRoom fmRoom : fmRooms) {
                                System.out.println("Room Number: " + fmRoom.getRoomNum() + " || Room cost: " + fmRoom.getRatePerDay());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(FamilyRoom fmRoom : fmRooms) {      //Find the room with room number inputted
                                if(fmRoom.getRoomNum()==pick) {
                                    room=fmRoom;
                                    isFound = true;
                                    return fmRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else break;
                            }
                        }
                    }else {
                        System.out.println("\nThere is no family rooms");
                        continue;
                    }

                    break;
                case 4:     //vip room
                    if (vpRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===VIP-ROOMS===");
                            for (VIPRoom vpRoom : vpRooms) {
                                System.out.println("Room Number: " + vpRoom.getRoomNum() +" || Room cost: " + vpRoom.getRatePerDay());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(VIPRoom vpRoom : vpRooms) {      //Find the room with room number inputted
                                if(vpRoom.getRoomNum()==pick) {
                                    room=vpRoom;
                                    isFound = true;
                                    return vpRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                break;
                            }
                        }
                    }else {
                        System.out.println("\nThere is no VIP rooms");
                        continue;
                    }
                    break;
                case 0:
                    isCategory = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
        return room;
    }

    public Room displaySelectRoomCategoryReservation(ArrayList<Room> rooms) {    //Display all room in category
        ArrayList<SingleRoom> snRooms = new ArrayList<SingleRoom>();
        ArrayList<CoupleRoom> cpRooms = new ArrayList<CoupleRoom>();
        ArrayList<FamilyRoom> fmRooms = new ArrayList<FamilyRoom>();
        ArrayList<VIPRoom> vpRooms = new ArrayList<VIPRoom>();
        Room room = null;

        sortRooms(rooms);

        for (int i = 0; i< rooms.size(); i++) {     // To separate each subclasses
            Room room1 = rooms.get(i);
            if(room1.getIsOccupied()==true) continue;
            if(room1.getIsDisabled()==true) continue;
            if(room1.getRoomType().equals("Single")==true) {snRooms.add((SingleRoom) room1);}
            else if(room1.getRoomType().equals("Couple")==true) {cpRooms.add((CoupleRoom) room1);}
            else if(room1.getRoomType().equals("Family")==true) fmRooms.add((FamilyRoom) room1);
            else if(room1.getRoomType().equals("VIP")==true) vpRooms.add((VIPRoom) room1);
        }

        boolean isCategory = true;
        while(isCategory==true) {
            if (rooms.size() != 0) System.out.println("\n===ROOMS-IN-CATEGORY===");
            else {
                System.out.println("\nThere is no room found!\n");
                return room;
            }

            System.out.println("[1]Single room");
            System.out.println("[2]Couple room");
            System.out.println("[3]Family room");
            System.out.println("[4]VIP room");
            System.out.println("[0]Back");
            System.out.print("Enter choice: ");
            pick = inputInt();

            switch (pick) {
                case 1:     //Single room
                    if (snRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===SINGLE-ROOMS===");
                            for (SingleRoom snRoom : snRooms) {
                                System.out.println("Room Number: " + snRoom.getRoomNum() + " || Room cost: " + snRoom.getReservationPrice());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(SingleRoom snRoom : snRooms) {      //Find the room with room number inputted
                                if(snRoom.getRoomNum()==pick) {
                                    room=snRoom;
                                    isFound = true;
                                    return snRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else break;
                            }
                        }
                    } else {
                        System.out.println("\nThere is no single room");
                        continue;
                    }
                    break;
                case 2:     //couple room
                    if (cpRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===COUPLE-ROOMS===");
                            for (CoupleRoom cpRoom : cpRooms) {
                                System.out.println("Room Number: " + cpRoom.getRoomNum() + " || Room cost: " + cpRoom.getReservationPrice());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(CoupleRoom cpRoom : cpRooms) {      //Find the room with room number inputted
                                if(cpRoom.getRoomNum()==pick) {
                                    room=cpRoom;
                                    isFound = true;
                                    return cpRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else break;
                            }
                        }
                    }else {
                        System.out.println("\nThere is no couple room");
                        continue;
                    }
                    break;
                case 3:     //Family room
                    if (fmRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===FAMILY-ROOMS===");
                            for (FamilyRoom fmRoom : fmRooms) {
                                System.out.println("Room Number: " + fmRoom.getRoomNum() + " || Room cost: " + fmRoom.getReservationPrice());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(FamilyRoom fmRoom : fmRooms) {      //Find the room with room number inputted
                                if(fmRoom.getRoomNum()==pick) {
                                    room=fmRoom;
                                    isFound = true;
                                    return fmRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else break;
                            }
                        }
                    }else {
                        System.out.println("\nThere is no family rooms");
                        continue;
                    }

                    break;
                case 4:     //vip room
                    if (vpRooms.size() != 0) {
                        boolean isFound = false;
                        while(isFound==false) {
                            System.out.println("\n===VIP-ROOMS===");
                            for (VIPRoom vpRoom : vpRooms) {
                                System.out.println("Room Number: " + vpRoom.getRoomNum() +" || Room cost: " + vpRoom.getReservationPrice());
                            }
                            System.out.print("Select room number: ");
                            pick = inputInt();

                            if(pick==0) break;
                            for(VIPRoom vpRoom : vpRooms) {      //Find the room with room number inputted
                                if(vpRoom.getRoomNum()==pick) {
                                    room=vpRoom;
                                    isFound = true;
                                    return vpRoom;
                                }
                            }

                            if(isFound==false)  {           //If room number is not found
                                System.out.println("\nINVALID: Room is not found");
                                break;
                            }
                        }
                    }else {
                        System.out.println("\nThere is no VIP rooms");
                        continue;
                    }
                    break;
                case 0:
                    isCategory = false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
        return room;
    }

    public void displayRoomByNum(ArrayList<Room> rooms) {       //Display room by sorted room number
        sortRoomByNum(rooms);
        for(Room room : rooms) {
            System.out.println("Room Number: " + room.getRoomNum() +
                    " || Room Type: " + room.getRoomType() +
                    " || Occupied: " + room.getIsOccupied() +
                    " || Disabled: " + room.getIsDisabled());
        }
    }

    public void displayCustomer(ArrayList<Customer> customers) {    //Display all customer
        for(int i = 0; i<customers.size();i++) {
            Customer customer = customers.get(i);
            System.out.print((i+1) + ". Name: " + customer.getName());
            if(customer.getEmail()!=null) System.out.print(" || Contact: " + customer.getEmail());
            System.out.println();
        }
    }

    public void displayStaff(ArrayList<Staff> staffs) {             //Display all staff
        for(int i = 0; i<staffs.size();i++) {
            Staff staff = staffs.get(i);
            System.out.print((i+1) + ". Name: " + staff.getName());
            if(staff.getEmail()!=null) System.out.print(" || Contact: " + staff.getEmail());
            System.out.println();
        }
    }

    public void editCustomer(ArrayList<Customer> customers) {       //Edit a customer
        String name = null;
        boolean isFound = false;
        Customer customer = null;
        while(true) {
            System.out.println("\n=====EDIT-ACCOUNT=====");
            System.out.print("Search name: ");
            name = sc.nextLine();

            for (int i = 0; i < customers.size(); i++) {
                Customer temp = customers.get(i);
                if (name.equals(temp.getName()) == true) {
                    customer = temp;
                    isFound = true;
                    break;
                }
            }
            if(isFound==false) {      //When account is not found
                boolean isCont = stateError("Account not found!");
                if (isCont == false) break;
                else continue;
            }

            if(customer!=null) {
                System.out.println("===ACCOUNT-INFO===");
                System.out.println("[1] Name: " + customer.getName());
                System.out.println("[2] Password: " + customer.getPassword());
                if(customer.getEmail()!=null) System.out.println("[3] Email: " + customer.getEmail());
                else System.out.println("[3] No email registered!");
                int choice = inputInt("Select to modify: ");
                switch (choice) {
                    case 1:
                        while(true) {
                            boolean isDup = false;
                            System.out.print("\nEnter new name: ");
                            String newName = sc.nextLine();

                            for (int i = 0; i < customers.size(); i++) {           //Check for duplicate name of customers
                                Customer customer1 = customers.get(i);
                                if (customer1.getName().equalsIgnoreCase(newName)) {
                                    System.out.println("INVALID: Name already exist");
                                    isDup=true;
                                }
                            }

                            if(isDup==true){
                                boolean isCont = isContinue();
                                if(isCont==true) continue;
                                else {
                                    System.out.println("\nModified name is unsuccessful");
                                    break;
                                }
                            }

                            customer.setName(newName);
                            System.out.println("Modified name successfully!");
                            break;
                        }
                        break;
                    case 2:
                        System.out.print("\nEnter new password: ");
                        String newPw = sc.nextLine();
                        customer.setPassword(newPw);
                        System.out.println("Modified password successfully!");
                        break;
                    case 3:
                        System.out.print("\nEnter new email: ");
                        String newEmail = sc.nextLine();
                        customer.setEmail(newEmail);
                        System.out.println("Modified email successfully!");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("INVALID: Use indicated number only!");
                }
            }
            break;
        }
    }

    public void editStaff(ArrayList<Staff> staffs) {        //Edit a staff account
        String name = null;
        boolean isFound = false;
        Staff staff = null;
        while(true) {
            System.out.println("\n=====EDIT-ACCOUNT=====");
            System.out.println("Search name: ");
            name = sc.nextLine();

            for (int i = 0; i < staffs.size(); i++) {
                Staff temp = staffs.get(i);
                if (name.equals(temp.getName()) == true) {
                    staff = temp;
                    isFound = true;
                    break;
                }
            }
            if (isFound = false) {      //When account is not found
                boolean isCont = stateError("Account not found!");
                if (isCont == false) break;
            }

            System.out.println("==ACCOUNT-INFO==");
            System.out.println("[1] Name: " + staff.getName());
            System.out.println("[2] Password: " + staff.getPassword());
            System.out.println("[3] Email: " + staff.getEmail());
            int choice = inputInt("Select to modify: ");
            switch(choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    staff.setName(newName);
                    System.out.println("Modified name successfully!");
                    break;
                case 2:
                    System.out.println("Enter new password: ");
                    String newPw = sc.nextLine();
                    staff.setPassword(newPw);
                    System.out.println("Modified password successfully!");
                    break;
                case 3:
                    System.out.println("Enter new email: ");
                    String newEmail = sc.nextLine();
                    staff.setEmail(newEmail);
                    System.out.println("Modified email successfully!");
                    break;
                case 0: break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
            break;
        }
    }

    public int isCustomerExistReturnIndex(ArrayList<Customer> customers, String name) {     //Returns an int index if customer exist
        int index = -1;
        for(int i = 0; i< customers.size(); i++) {
            if(customers.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int isStaffExistReturnIndex(ArrayList<Staff> staffs, String name) {     //Returns a boolean if room exist
        int index = -1;
        for(int i = 0; i< staffs.size(); i++) {
            if(staffs.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Person searchPerson(ArrayList<Person> persons, String accountName) {     //Returns the Person object if person is found
        String acctName = null;
        String password = null;
        Person person = null;
        boolean isSearch = true;

        while(isSearch==true) {
            acctName = accountName;
            //Checks if Account is found
            boolean isFound = false;
            int pos = 0;
            for (int i = 0; i < persons.size(); i++) {
                person = persons.get(i);
                if (person.getName().equals(acctName)) {      //Checks if the same
                    isFound = true;
                    pos = i;
                    break;
                }
            }
            if (isFound == false) {        //ask if continue or not
                System.out.println("INVALID: Account name not found");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                else break;
            }

            System.out.println("Enter password");
            password = sc.nextLine();

            //Checks if the password is correct in the account name
            boolean isPword = pwordCheckPerson(persons, password, pos);
            if (isPword == false) {
                System.out.println("INVALID: Password is incorrect");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                else break;
            }

            person = persons.get(pos);
        }
        return person;
    }

    public Customer searchCustomer(ArrayList<Customer> customers) {     //Returns the Customer object if its found
        String acctName = null;
        String password = null;
        Customer customer = null;
        boolean isSearch = true;

        Search : while(isSearch==true) {
            System.out.print("Search account name: ");
            acctName = sc.nextLine();
            //Checks if Account is found
            boolean isFound = false;
            int pos = 0;
            for (int i = 0; i < customers.size(); i++) {
                customer = customers.get(i);
                if (customer.getName().equals(acctName)) {      //Checks if the same
                    isFound = true;
                    pos = i;
                    break;
                }
            }
            if (isFound == false) {        //ask if continue or not
                System.out.println("INVALID: Account name not found");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                return null;
            }

            System.out.print("Enter password: ");
            password = sc.nextLine();

            //Checks if the password is correct in the account name
            boolean isPword = pwordCheckCustomer(customers, password, pos);
            if (isPword == false) {
                System.out.println("INVALID: Password is incorrect");
                boolean isCont = isContinue();
                if (isCont == true) continue;
                return null;
            }
            customer = customers.get(pos);
        }
        return customer;
    }

    public boolean checkDupPerson(ArrayList<Person> persons, String s) {    //Returns the boolean whether there is duplicate in person name
        boolean isDuplicate = false;

        for(int i = 0; i<persons.size(); i++) {
            Person temp = persons.get(i);
            if(temp.getName().equals(s)) {
                isDuplicate=true;
                break;
            }
        }

        return isDuplicate;
    }

    public boolean checkDupCustomer(ArrayList<Customer> persons, String s) {       //Returns the boolean whether there is duplicate in customer name
        boolean isDuplicate = false;

        for(int i = 0; i<persons.size(); i++) {
            Person temp = persons.get(i);
            if(temp.getName().equals(s)) {
                isDuplicate=true;
                break;
            }
        }

        return isDuplicate;
    }

    public boolean checkDupStaff(ArrayList<Staff> persons, String s) {         //Returns the boolean whether there is duplicate in staff name
        boolean isDuplicate = false;

        for(int i = 0; i<persons.size(); i++) {
            Person temp = persons.get(i);
            if(temp.getName().equals(s)) {
                isDuplicate=true;
                break;
            }
        }

        return isDuplicate;
    }

    public boolean nameFinder(ArrayList<Customer> customers, String s) {       //Returns a boolean if customer name is found
        boolean isFound = false;

        for(int i = 0; i<customers.size(); i++) {
            Customer customer = customers.get(i);
            if(customer.getName().equals(s)) {      //Checks if the same
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public boolean pwordCheckPerson(ArrayList<Person> persons, String p, int pos) {    //pos determines the index/postion from the Array
        boolean isFound = false;

        Person person = persons.get(pos);
        if(person.getPassword().equals(p)) isFound = true;

        return isFound;
    }

    public boolean pwordCheckCustomer(ArrayList<Customer> customers, String p, int pos) {    //pos determines the index/postion from the Array
        boolean isFound = false;

        Customer customer = customers.get(pos);
        if(customer.getPassword().equals(p)) isFound = true;

        return isFound;
    }

    public boolean isContinue() {       //Return boolean if still want to continue or not
        String choice = null;
        while(true) {
            System.out.println("Do you want to continue? [1]Yes/[2]No");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
            }
        }
    }

    public void isGoContinue() {
        boolean isContinue = true;
        while(isContinue==true) {
            System.out.print("Press 1 to continue: ");
            pick = inputInt();
            switch (pick) {
                case 1:
                    isContinue=false;
                    break;
                default:
                    System.out.println("INVALID: Use indicated number only!");
            }
        }
    }

    public boolean isContinue(String statement) {       //Return boolean if still want to continue or not
        String choice = null;
        while(true) {
            System.out.print(statement);
            System.out.println(" [1]Yes/[2]No");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
            }
        }
    }

    public boolean isGoBack() {     //This is to ask user if go back for avoiding print issue
        while(true) {
            System.out.print("Press 0 to go back: ");
            choice = sc.nextLine();

            switch (choice) {
                case "0":
                    return true;
                default:
                    System.out.println("INVALID: Press 0 key only");
            }
        }
    }

    public boolean stateError(String statement) {      //Used when there is invalid
        System.out.println("INVALID: " + statement);
        boolean isCont = isContinue();
        if(isCont==true) return true;
        else return false;
    }

    public int inputInt(String statement) { //For taking an integer input
        int choiceNum = -1;
        try {
            System.out.print(statement);
            String choice = sc.nextLine();
            boolean isAllNum = digitChecker(choice);
            choiceNum = StrToInt(choice);
        } catch(Exception e) {
            System.out.println("INVALID: Input numbers only!");
            return -1;
        }
        return choiceNum;
    }

    public int inputInt() {
        int choiceNum = -1;
        try {
            String choice = sc.nextLine();
            boolean isAllNum = digitChecker(choice);
            choiceNum = StrToInt(choice);
        } catch(Exception e) {
            return -1;
        }
        return choiceNum;
    }

    public double inputDouble() {
        double choiceNum = -1;
        try {
            String choice = sc.nextLine();
            boolean isAllNum = doubleChecker(choice);
            choiceNum = StrToDbl(choice);
        } catch(Exception e) {
            return -1;
        }
        return choiceNum;
    }

    public boolean doubleChecker(String s) {
        boolean isDigit = true;

        //checks every digit if all is numbers
        for(int i = 0;i<s.length(); i++) {
            String ch = Character.toString(s.charAt(i));
            if(Character.isDigit(s.charAt(i))!=true) {
                if(ch.equals(".")) {
                    continue;
                }else {
                    isDigit = false;
                    break;
                }
            }
        }

        return isDigit;
    }

    boolean digitChecker(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)!=true) {
                //System.out.println("There is a string: " + ch);
                return false;
            }
        }
        return true;
    }

    void sortRooms(ArrayList<Room> rooms) {
        ArrayList<SingleRoom> snRooms = new ArrayList<SingleRoom>();
        ArrayList<CoupleRoom> cpRooms = new ArrayList<CoupleRoom>();
        ArrayList<FamilyRoom> fmRooms = new ArrayList<FamilyRoom>();
        ArrayList<VIPRoom> vpRooms = new ArrayList<VIPRoom>();

        for (int i = 0; i< rooms.size(); i++) {     // To separate each subclasses
            if(rooms.get(i).getRoomType().equals("Single")==true) {snRooms.add((SingleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Couple")==true) {cpRooms.add((CoupleRoom) rooms.get(i));}
            else if(rooms.get(i).getRoomType().equals("Family")==true) fmRooms.add((FamilyRoom) rooms.get(i));
            else if(rooms.get(i).getRoomType().equals("VIP")==true) vpRooms.add((VIPRoom) rooms.get(i));
        }

        rooms.removeAll(rooms);

        for (int i = 0; i < snRooms.size(); i++) {         //Sort the singleRoomNum by roomNums
            for (int j = i + 1; j < snRooms.size(); j++) {
                SingleRoom snRoom = snRooms.get(i);
                SingleRoom snRoom2 = snRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    snRooms.set(i, snRoom2);
                    snRooms.set(j, snRoom);
                }
            }
        }

        for(int i = 0; i<cpRooms.size(); i++) {         //Sort the coupleRoomNum by roomNums
            for(int j = i+1; j<cpRooms.size(); j++) {
                CoupleRoom snRoom = cpRooms.get(i);
                CoupleRoom snRoom2 = cpRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    cpRooms.set(i, snRoom2);
                    cpRooms.set(j, snRoom);
                }
            }
        }

        for(int i = 0; i<fmRooms.size(); i++) {         //Sort the familyRoomNum by roomNums
            for(int j = i+1; j<fmRooms.size(); j++) {
                FamilyRoom snRoom = fmRooms.get(i);
                FamilyRoom snRoom2 = fmRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    fmRooms.set(i, snRoom2);
                    fmRooms.set(j, snRoom);
                }
            }
        }

        for(int i = 0; i<vpRooms.size(); i++) {         //Sort the familyRoomNum by roomNums
            for(int j = i+1; j<vpRooms.size(); j++) {
                VIPRoom snRoom = vpRooms.get(i);
                VIPRoom snRoom2 = vpRooms.get(j);
                if (snRoom.getRoomNum() > snRoom2.getRoomNum()) {
                    vpRooms.set(i, snRoom2);
                    vpRooms.set(j, snRoom);
                }
            }
        }

        for(Room room: snRooms) {rooms.add(room);}
        for(Room room: cpRooms) {rooms.add(room);}
        for(Room room: fmRooms) {rooms.add(room);}
        for(Room room: vpRooms) {rooms.add(room);}

    }

    public void sortRoomByNum(ArrayList<Room> rooms) {
        for(int i = 0; i<rooms.size(); i++) {
            for(int j = i+1; j<rooms.size(); j++) {
                Room room1= rooms.get(i);
                Room room2 = rooms.get(j);
                if(room1.getRoomNum()>room2.getRoomNum()) {
                    rooms.set(i, room2);
                    rooms.set(j, room1);
                }
            }
        }
    }


    //DATATYPE CONVERTER
    public int StrToInt(String s) {
        int num = 0;
        num = Integer.parseInt(s);
        return num;
    }

    public double StrToDbl(String s) {
        double num = 0;
        num = Double.parseDouble(s);
        return num;
    }

    public String IntToStr(int n) {
        String s = null;
        s = Integer.toString(n);
        return s;
    }

    public String DblToStr(double n) {
        String s = null;
        s = Double.toString(n);
        return s;
    }

    //===============================NEW ADDED METHODS===========================

    public void displayMenus(ArrayList<Menu> menus) {
        System.out.println("\n=====MENU-LIST=====");
        if(menus.size()==0) System.out.println("There is no menu created");
        for(int i = 0; i<menus.size(); i++) {        //Display all menu created
            Menu menu = menus.get(i);
            System.out.print((i+1) + " Name: " + menu.getMenuName()
                    + " || Food: " + menu.getMainDish().getFoodName());
            if(menu.getSideDish() !=null) System.out.print(" , " + menu.getSideDish().getFoodName());
            if(menu.getDrinks()!=null) System.out.print(" , " + menu.getDrinks().getFoodName());
            if(menu.getDessert()!=null) System.out.print(" , " + menu.getDessert().getFoodName());
            System.out.print(" || Price: " + menu.getTotalPrice());
            System.out.println("");
        }
    }

    public Food createMenuOrder(ArrayList<Food> inventory, Menu menuSelect) {   //Delete the stocks in the inventory, return food that have 0 stocks
        Food foodConflict = null;          //If the food return is null the create menu is successful

        //Checks the
        if(menuSelect.getMainDish()!=null) {
            MainDish main =(MainDish) menuSelect.getMainDish();
            if(main.getStocks()<=0) {   //If the food have 0 stocks end the order
                return main;
            }else {                     //If the food have inventory
                main.setStocks(main.getStocks()-1);
            }
        }
        if(menuSelect.getSideDish()!=null) {
            SideDish side =(SideDish) menuSelect.getSideDish();
            if(side.getStocks()<=0) {   //If the food have 0 stocks end the order
                return side;
            }else {                     //If the food have inventory
                side.setStocks(side.getStocks()-1);
            }
        }
        if(menuSelect.getDessert()!=null) {
            Dessert dessert =(Dessert) menuSelect.getDessert();
            if(dessert.getStocks()<=0) {   //If the food have 0 stocks end the order
                return dessert;
            }else {                     //If the food have inventory
                dessert.setStocks(dessert.getStocks()-1);
            }
        }
        if(menuSelect.getDrinks()!=null) {
            Drinks drink =(Drinks) menuSelect.getDrinks();
            if(drink.getStocks()<=0) {   //If the food have 0 stocks end the order
                return drink;
            }else {                     //If the food have inventory
                drink.setStocks(drink.getStocks()-1);
            }
        }
        return foodConflict;
    }

    public Menu selectMenu(ArrayList<Menu> menus) {
        Menu menuSelect = null;
        while(true) {
            System.out.println("\n=====MENU-LIST=====");
            if (menus.size() == 0) System.out.println("There is no menu created");
            for (int i = 0; i < menus.size(); i++) {        //Display all menu created
                Menu menu = menus.get(i);
                System.out.print((i + 1) + " Name: " + menu.getMenuName()
                        + " || Food: " + menu.getMainDish().getFoodName());
                if (menu.getSideDish() != null) System.out.print(" , " + menu.getSideDish().getFoodName());
                if (menu.getDrinks() != null) System.out.print(" , " + menu.getDrinks().getFoodName());
                if (menu.getDessert() != null) System.out.print(" , " + menu.getDessert().getFoodName());
                System.out.print(" || Price: " + menu.getTotalPrice());
                System.out.println("");
            }

            System.out.print("\nSelect menu number: ");
            int menuNum = inputInt();
            if(menuNum>menus.size() || menuNum<0) {
                System.out.println("INVALID: Pick number with existing menu or 0 to exit");
                continue;
            }
            if(menuNum==0) break;
            menuSelect = menus.get(menuNum-1);

            break;
        }
        if(menuSelect!=null) return menuSelect;
        else return null;
    }

    public int selectMenuIndex(ArrayList<Menu> menus) {
        int menuIndex = -1;
        while(true) {
            System.out.println("=====MENU-LIST=====");
            if (menus.size() == 0) System.out.println("There is no menu created");
            for (int i = 0; i < menus.size(); i++) {        //Display all menu created
                Menu menu = menus.get(i);
                System.out.print((i + 1) + " Name: " + menu.getMenuName()
                        + " || Food: " + menu.getMainDish().getFoodName());
                if (menu.getSideDish() != null) System.out.print(" , " + menu.getSideDish().getFoodName());
                if (menu.getDrinks() != null) System.out.print(" , " + menu.getDrinks().getFoodName());
                if (menu.getDessert() != null) System.out.print(" , " + menu.getDessert().getFoodName());
                System.out.print(" || Price: " + menu.getTotalPrice());
                System.out.println("");
            }

            System.out.print("\nSelect menu number: ");
            int menuNum = inputInt();
            if(menuNum>menus.size() || menuNum<0) {
                System.out.println("INVALID: Pick number with existing menu or 0 to exit");
                continue;
            }
            if(menuNum==0) break;
            menuIndex = menuNum;
            break;
        }
        if(menuIndex==-1) return -1;
        else return menuIndex;
    }

    public Menu checkMenuExist(ArrayList<Menu> menus, String menuName) {    //Returns a menu if that menuname existed in list
        Menu foundMenu = null;
        for(int i = 0; i<menus.size(); i++) {
            Menu menu = menus.get(i);
            if(menu.getMenuName().equals(menuName)==true) {
                foundMenu = menu;
                break;
            }
        }
        return foundMenu;
    }

    public Room selectRoom(ArrayList<Room> rooms) {     //select Room and check if it is vacant
        Room room = null;
        boolean isFound = false;                            //True if the room is found with same roomNum
        ArrayList<Room> roomAvailable = new ArrayList<>();  //Store the not occupied and not disabled room

        for(int i = 0; i<rooms.size(); i++) {                               //Add the available room in roomAvailable list
            Room room2 = rooms.get(i);
            if(room2.getIsOccupied()==true || room2.getIsDisabled()==true) {        //Skip the unavailable rooms
                continue;
            }
            roomAvailable.add(room2);
        }

        if(roomAvailable.size()!=0) {       //If there is room existing
            System.out.println("\n===ROOMS-LIST===");
            for (int i = 0; i < roomAvailable.size(); i++) {
                Room room1 = roomAvailable.get(i);
                System.out.println((i + 1) + ". Room Number: " + room1.getRoomNum() + " || Room Type: " + room1.getRoomType()
                            + " || Cost: " + room1.getRatePerDay());
            }
        }else System.out.println("\nThere is no room found\n");

        while(isFound==false) {
            int roomNum = 0;
            System.out.print("Enter room number: ");
            roomNum = inputInt();

            for(int i = 0; i<roomAvailable.size(); i++) {
                Room temp = roomAvailable.get(i);
                if(temp.getRoomNum()==roomNum) {
                    room = temp;
                    isFound=true;
                    break;
                }
            }

            if(isFound==false) {
                System.out.println("INVALID: Room not found with the specified room number");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }
        }
        return room;
    }

    public Room selectRoomReservation(ArrayList<Room> rooms) {     //select Room and check if it is vacant
        Room room = null;
        boolean isFound = false;                            //True if the room is found with same roomNum
        ArrayList<Room> roomAvailable = new ArrayList<>();  //Store the not occupied and not disabled room

        for(int i = 0; i<rooms.size(); i++) {                               //Add the available room in roomAvailable list
            Room room2 = rooms.get(i);
            if(room2.getIsOccupied()==true || room2.getIsDisabled()==true) {        //Skip the unavailable rooms
                continue;
            }
            roomAvailable.add(room2);
        }

        if(roomAvailable.size()!=0) {       //If there is room existing
            System.out.println("\n===ROOMS-LIST===");
            for (int i = 0; i < roomAvailable.size(); i++) {
                Room room1 = roomAvailable.get(i);
                System.out.println((i + 1) + ". Room Number: " + room1.getRoomNum() + " || Room Type: " + room1.getRoomType()
                        + " || Cost: " + room1.getReservationPrice());
            }
        }else System.out.println("\nThere is no room found\n");

        while(isFound==false) {
            int roomNum = 0;
            System.out.print("Enter room number: ");
            roomNum = inputInt();

            for(int i = 0; i<roomAvailable.size(); i++) {
                Room temp = roomAvailable.get(i);
                if(temp.getRoomNum()==roomNum) {
                    room = temp;
                    isFound=true;
                    break;
                }
            }

            if(isFound==false) {
                System.out.println("INVALID: Room not found with the specified room number");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }
        }
        return room;
    }

    public Amenity selectAmenity(ArrayList<Amenity> amenities) {     //select amenity and check if it is vacant
        sortAmenity(amenities);
        Amenity amenity = null;
        boolean isFound = false;
        ArrayList<Amenity> amenityAvailable = new ArrayList<>();

        for(int i = 0; i<amenities.size(); i++) {
            Amenity amenity1 = amenities.get(i);
            if(amenity1.getIsReserved()==true) {        //Skip the unavailable amenity
                continue;
            }
            amenityAvailable.add(amenity1);
        }

        //Display amenity available
        System.out.println("\n=====AMENITIES-LIST=====");
        for(int i = 0; i<amenityAvailable.size(); i++) {
            Amenity amenity1 = amenityAvailable.get(i);
            System.out.print(i+1 + " ");

            if(amenity1.getClass()==Karaoke.class) {     //Display the type of amenity
                System.out.print("Karaoke");
            }else if(amenity1.getClass()==ReceptionHall.class) {
                System.out.print("Reception Hall");
            }else if(amenity1.getClass()==Pool.class) {
                System.out.print("Pool");
            }else if(amenity1.getClass()==GameRoom.class){
                System.out.print("Game Room");
            }

            System.out.print(" || Amenity Code: " + amenity1.getAmenityCode());
            System.out.println(" || Reservation cost: " + amenity1.getReservationCost());
        }

        while(isFound==false) {
            String amenityCode = null;
            System.out.print("Enter amenity code:");
            amenityCode = sc.nextLine();

            for(int i = 0; i<amenityAvailable.size(); i++) {
                Amenity temp = amenityAvailable.get(i);
                if(temp.getAmenityCode().equalsIgnoreCase(amenityCode)) {
                    amenity=temp;
                    isFound=true;
                    break;
                }
            }

            if(isFound==false) {
                System.out.println("INVALID: Amenity not found with the specified amenity code");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }
        }
        return amenity;
    }

    public boolean checkReservation(ArrayList<Reservation> reservations, Reservation reserve) {
        boolean isGood = true;      //Signs if there is a conflict or no conflict in date
        boolean isRoom = false;
        boolean isAmenity = false;

        //Checks whether reserve is amenity or room reserve
        if(reserve.getRoom()!=null) {
            isRoom = true;
        } else if(reserve.getAmenity()!=null) {
            isAmenity = true;
        }

        if(isRoom==true) {
            for(int i = 0; i< reservations.size(); i++) {
                Reservation reserveList = reservations.get(i);
                if(reserveList.getRoom()!=null) {   //Reservation is room

                    Room roomList = reserveList.getRoom();  //Get the room reservation in list
                    Room room = reserve.getRoom();          //Get the room reservation in reserve
                    if(roomList.getRoomNum()==room.getRoomNum()==false) continue;       //Dili same room
                    ArrayList<Date> durationDays = reserveList.getDuration(reserveList.getStartDate(), reserveList.getDuration());
                    for(int j = 0; j<durationDays.size(); j++) {        //Iterate for Date in durationDays compareTo rsrvation obj
                        Date date = durationDays.get(j);                //Get the date in durationDays
                        ArrayList<Date> durationToRsrv = reserve.getDuration(reserve.getStartDate(), reserve.getDuration());
                        for(int k = 0; k<durationToRsrv.size(); k++) {
                            if(compareDate(durationToRsrv.get(k),date)==0) {
                                isGood = false;     //There is a conflict in Date;
                                return false;
                            }
                        }
                    }
                }else continue;
            }
        }else if(isAmenity==true) {
            for(int i = 0; i< reservations.size(); i++) {
                Reservation reserveList = reservations.get(i);
                if(reserveList.getAmenity()!=null) {   //Reservation is amenity
                    Amenity amenityList = reserveList.getAmenity();  //Get the amenity reservation in list
                    Amenity amenity = reserve.getAmenity();          //Get the amenity reservation in reserve

                    if(amenityList.getAmenityCode().equalsIgnoreCase(amenity.getAmenityCode())==false) continue;       //Dili same amenity

                    ArrayList<Date> durationDays = reserveList.getDuration(reserveList.getStartDate(), reserveList.getDuration());
                    for(int j = 0; j<durationDays.size(); j++) {        //Iterate for Date in durationDays compareTo rsrvation obj
                        Date date = durationDays.get(j);                //Get the date in durationDays
                        ArrayList<Date> durationToRsrv = reserve.getDuration(reserve.getStartDate(), reserve.getDuration());
                        for(int k = 0; k<durationToRsrv.size(); k++) {
                            if(compareDate(durationToRsrv.get(k), date)==0) {
                                //System.out.println("Conflict in i: " + (i) + " || j: " + (j) + " || k: " + (k));
                                isGood = false;     //There is a conflict in Date;
                            }
                        }
                    }
                }else continue;
            }
        }
        return isGood;
    }

    public boolean checkReservation(ArrayList<Reservation> reservationsMain, Reservation reserve, Reservation exempted) {       //Use for exempted reservation
        boolean isGood = true;      //Signs if there is a conflict or no conflict in date
        boolean isRoom = false;
        boolean isAmenity = false;
        ArrayList<Reservation> reservations = new ArrayList<>();

        //Transferring the reservationsMain obj to reservations
        for(int i = 0; i<reservationsMain.size(); i++) {
            reservations.add(new Reservation(reservationsMain.get(i)));
        }

        //Remove the exempted on the list
        reservations.remove(exempted);

        //Checks whether reserve is amenity or room reserve
        if(reserve.getRoom()!=null) {
            isRoom = true;
        } else if(reserve.getAmenity()!=null) {
            isAmenity = true;
        }

        if(isRoom==true) {
            for(int i = 0; i< reservations.size(); i++) {
                Reservation reserveList = reservations.get(i);
                if(reserveList.getRoom()!=null) {   //Reservation is room

                    Room roomList = reserveList.getRoom();  //Get the room reservation in list
                    Room room = reserve.getRoom();          //Get the room reservation in reserve
                    if(roomList.getRoomNum()==room.getRoomNum()==false) continue;       //Dili same room
                    ArrayList<Date> durationDays = reserveList.getDuration(reserveList.getStartDate(), reserveList.getDuration());
                    for(int j = 0; j<durationDays.size(); j++) {        //Iterate for Date in durationDays compareTo rsrvation obj
                        Date date = durationDays.get(j);                //Get the date in durationDays
                        ArrayList<Date> durationToRsrv = reserve.getDuration(reserve.getStartDate(), reserve.getDuration());
                        for(int k = 0; k<durationToRsrv.size(); k++) {
                            if(compareDate(durationToRsrv.get(k),date)==0) {
                                isGood = false;     //There is a conflict in Date;
                                return false;
                            }
                        }
                    }
                }else continue;
            }
        }else if(isAmenity==true) {
            for(int i = 0; i< reservations.size(); i++) {
                Reservation reserveList = reservations.get(i);
                if(reserveList.getAmenity()!=null) {   //Reservation is amenity
                    Amenity amenityList = reserveList.getAmenity();  //Get the amenity reservation in list
                    Amenity amenity = reserve.getAmenity();          //Get the amenity reservation in reserve

                    if(amenityList.getAmenityCode().equalsIgnoreCase(amenity.getAmenityCode())==false) continue;       //Dili same amenity

                    ArrayList<Date> durationDays = reserveList.getDuration(reserveList.getStartDate(), reserveList.getDuration());
                    for(int j = 0; j<durationDays.size(); j++) {        //Iterate for Date in durationDays compareTo rsrvation obj
                        Date date = durationDays.get(j);                //Get the date in durationDays
                        ArrayList<Date> durationToRsrv = reserve.getDuration(reserve.getStartDate(), reserve.getDuration());
                        for(int k = 0; k<durationToRsrv.size(); k++) {
                            if(compareDate(durationToRsrv.get(k), date)==0) {
                                //System.out.println("Conflict in i: " + (i) + " || j: " + (j) + " || k: " + (k));
                                isGood = false;     //There is a conflict in Date;
                            }
                        }
                    }
                }else continue;
            }
        }
        return isGood;
    }

    public void displayReservations2(ArrayList<ReserveTransact> reserveTransacts) {
        ArrayList<ReserveTransact> roomReservation = new ArrayList<>();
        ArrayList<ReserveTransact> amenityReservation = new ArrayList<>();

        System.out.println("\n=====RESERVATIONS=====");
        if(reserveTransacts.size()!=0) {
            for(int i = 0; i<reserveTransacts.size(); i++) {                //Filter the reserve transacts between room and amenity
                ReserveTransact reserveTrans = reserveTransacts.get(i);
                Reservation reservation = reserveTrans.getReservation();
                if(reservation.getRoom()!=null) roomReservation.add(reserveTrans);
                else if(reservation.getAmenity()!=null) amenityReservation.add(reserveTrans);
            }
        }else if(reserveTransacts.size()==0) {
            System.out.println("There is no reservations");
            return;
        }

        System.out.println("=====ROOM-RESERVED=====");
        if(roomReservation.size()!=0) {
            for(int i = 0; i<roomReservation.size(); i++) {
                ReserveTransact transact = roomReservation.get(i);
                Reservation reservation = transact.getReservation();
                Room room = reservation.getRoom();
                System.out.print("[" + (i + 1) + "] " + "Room " + room.getRoomNum() + " || Type: " +
                        room.getRoomType());
                System.out.print(" || Date: " );
                reservation.getStartDate().displayDate2();
                System.out.print(" || Duration: " + reservation.getDuration());
                if (reservation.getDuration() == 1) System.out.print("day");
                else System.out.print("days");
                System.out.println(" || Cost: " + transact.getBills());
            }
        }else {
            System.out.println("There is no room reserved");
        }

        System.out.println("=====AMENITY-RESERVED=====");
        if(amenityReservation.size()!=0) {
            for(int i = 0; i<amenityReservation.size(); i++) {
                ReserveTransact transact = amenityReservation.get(i);
                Reservation reservation = transact.getReservation();
                Amenity amenity = reservation.getAmenity();
                System.out.print("[" + (i + 1) + "] " + "Amenity code " + amenity.getAmenityCode() + " || Type: " +
                        amenity.getAmenityType());
                System.out.print(" || Date: " );
                reservation.getStartDate().displayDate2();
                System.out.print(" || Duration: " + reservation.getDuration());
                if (reservation.getDuration() == 1) System.out.print("day");
                else System.out.print("days");
                System.out.println(" || Cost: " + transact.getBills());
            }
        }else {
            System.out.println("There is no amenity reserved");
        }
    }

    public void displayReservations(ArrayList<Reservation> reservations) {
        System.out.println("\n=====RESERVATIONS=====");
        if(reservations.size()==0) System.out.println("There is no reservations");
        ArrayList<Reservation> roomRsrv = new ArrayList<>();
        ArrayList<Reservation> amenityRsrv = new ArrayList<>();

        for(int i = 0; i< reservations.size(); i++) {       //Filter the kind of reservation
            Reservation reservation = reservations.get(i);
            if(reservation.getRoom()!=null) {
                roomRsrv.add(reservation);
            }else if(reservation.getAmenity()!=null) {
                amenityRsrv.add(reservation);
            }
        }

        System.out.println("=====ROOM-RESERVED=====");
        if(roomRsrv.size()!=0) {
            for(int i = 0; i<roomRsrv.size(); i++) {
                Reservation reservation = roomRsrv.get(i);
                Room room = reservation.getRoom();
                System.out.print("[" + (i + 1) + "] " + "Room " + room.getRoomNum() + " || Type: " +
                        room.getRoomType());
                System.out.print(" || Date: " );
                reservation.getStartDate().displayDate2();
                System.out.print(" || Duration: " + reservation.getDuration());
                if (reservation.getDuration() == 1) System.out.println("day");
                else System.out.println("days");
            }
        }else {
            System.out.println("There is no room reserved");
        }

        System.out.println("=====AMENITY-RESERVED=====");
        if(amenityRsrv.size()!=0) {
            for(int i = 0; i<amenityRsrv.size(); i++) {
                Reservation reservation = amenityRsrv.get(i);
                Amenity amenity = reservation.getAmenity();
                System.out.print("[" + (i + 1) + "] " + "Amenity code " + amenity.getAmenityCode() + " || Type: " +
                        amenity.getAmenityType());
                System.out.print(" || Date: " );
                reservation.getStartDate().displayDate2();
                System.out.print(" || Duration: " + reservation.getDuration());
                if (reservation.getDuration() == 1) System.out.println("day");
                else System.out.println("days");
            }
        }else {
            System.out.println("There is no amenity reserved");
        }
    }

    public ArrayList<ReserveTransact> getReserveTransact(ArrayList<Transact> transacts) {
        ArrayList<ReserveTransact> reserveTransacts = new ArrayList<>();

        if(transacts.size()!=0){    //Have transactions
            for(Transact transact: transacts) {
                if(transact.getClass()==ReserveTransact.class) reserveTransacts.add((ReserveTransact) transact);
            }
        }
        return reserveTransacts;
    }

    public void displayReserveTransacts(ArrayList<ReserveTransact> reserveTransacts) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        if(reserveTransacts.size()!=0) {    //There is a reserve transaction
            for(int i = 0; i<reserveTransacts.size(); i++) {
                Reservation reservation = reserveTransacts.get(i).getReservation();
                reservations.add(reservation);
            }
        }

        System.out.println("\n=====RESERVATIONS=====");
        if(reservations.size()==0) System.out.println("There is no reservations");
        ArrayList<Reservation> roomRsrv = new ArrayList<>();
        ArrayList<Reservation> amenityRsrv = new ArrayList<>();

        for(int i = 0; i< reservations.size(); i++) {       //Filter the kind of reservation
            Reservation reservation = reservations.get(i);
            if(reservation.getRoom()!=null) {
                roomRsrv.add(reservation);
            }else if(reservation.getAmenity()!=null) {
                amenityRsrv.add(reservation);
            }
        }

        System.out.println("=====ROOM-RESERVED=====");
        if(roomRsrv.size()!=0) {
            for(int i = 0; i<roomRsrv.size(); i++) {
                Reservation reservation = roomRsrv.get(i);
                Room room = reservation.getRoom();
                System.out.print("[" + (i + 1) + "] " + "Room " + room.getRoomNum() + " || Type: " +
                        room.getRoomType());
                System.out.print(" || Date: " );
                reservation.getStartDate().displayDate2();
                System.out.print(" || Duration: " + reservation.getDuration());
                if (reservation.getDuration() == 1) System.out.print("day");
                else System.out.print("days");
                System.out.println(" || Cost: " + reservation.computeReservationPrice());
            }
        }else {
            System.out.println("There is no room reserved");
        }

        System.out.println("=====AMENITY-RESERVED=====");
        if(amenityRsrv.size()!=0) {
            for(int i = 0; i<amenityRsrv.size(); i++) {
                Reservation reservation = amenityRsrv.get(i);
                Amenity amenity = reservation.getAmenity();
                System.out.print("[" + (i + 1) + "] " + "Amenity code " + amenity.getAmenityCode() + " || Type: " +
                        amenity.getAmenityType());
                System.out.print(" || Date: " );
                reservation.getStartDate().displayDate2();
                System.out.print(" || Duration: " + reservation.getDuration());
                if (reservation.getDuration() == 1) System.out.print("day");
                else System.out.print("days");
                System.out.println(" || Cost: " + reservation.computeReservationPrice());
            }
        }else {
            System.out.println("There is no amenity reserved");
        }
    }

    public Date inputDate() {
        boolean isInput = true;
        Date date = null;

        while(isInput==true) {
            System.out.println("\n===SET-DATE===");
            System.out.print("Day: ");
            int day = inputInt();

            if(day>31 || day<1) {     //Scans if date is proper
                System.out.println("INVALID: Date should be valid(1-31)");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }

            System.out.print("Month: ");
            int month = inputInt();

            if(month>12 || month<1) {     //Scans if date is proper
                System.out.println("INVALID: Month should be valid(1-12)!");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }

            System.out.print("Year: ");
            int year = inputInt();

            if(year<2024) {     //Scans if date is proper
                System.out.println("INVALID: Year should not be less than 2024");
                boolean isCont = isContinue();
                if(isCont==true) continue;
                else break;
            }

            date = new Date(day, month, year);

            if(compareDate(date, Main.globalDate)==-1) {
                System.out.println("INVALID: You cannot select date from the past");
                continue;
            }

            break;
        }
        return date;
    }

    public int compareDate(Date date, Date date2) {      //Date1 and date2  //0 if equal, -1 if date less than date2, 1 if date greater than date2
        if(date.getYear()==date2.getYear()) {                   //Same year
            if(date.getMonth()==date2.getMonth()) {             //Same month
                if(date.getDate()==date2.getDate()) {           //Same date
                    return 0;
                }else if(date.getDate()<date2.getDate()) {     //date1 date is lesser than
                    return -1;
                }else {
                    return 1;
                }
            }else if(date.getMonth()<date2.getMonth()) {        //date1 month lesser than date 2
                return -1;
            }else {                                             //date1 month lesser than date 2
                return 1;
            }
        }else if(date.getYear()<date2.getYear()) {              //date1 year lesser than date2
            return -1;
        }else {                                                 //date 1 year higher than date2
            return 1;
        }
    }

    public int getReservationRoomIndex(ArrayList<Reservation> reservations, int roomNum) {
        int index = -1;

        for(int i = 0; i< reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if(reservation.getRoom()!=null) {
                if (reservation.getRoom().getRoomNum() == roomNum) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public int getReservationAmenityIndex(ArrayList<Reservation> reservations, String amenity) {
        int index = -1;

        for(int i = 0; i< reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if(reservation.getAmenity()!=null) {
                if (reservation.getAmenity().getAmenityCode().equalsIgnoreCase(amenity)) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    public ArrayList<ReserveTransact> getReservationTransact(ArrayList<Transact> transacts) {
        ArrayList<ReserveTransact> reservations = new ArrayList<>();

        for(int i = 0; i< transacts.size(); i++) {
            Transact transact = transacts.get(i);
            if(transact.getClass()==ReserveTransact.class) {
                reservations.add((ReserveTransact) transact);
            }
        }
        return reservations;
    }

    public HotelTransact getHotelTransact(ArrayList<Transact> transacts) {
        HotelTransact hotelTransact = null;

        for(int i = 0; i< transacts.size(); i++) {
            Transact transact = transacts.get(i);
            if(transact.getClass()==HotelTransact.class) {
                hotelTransact = (HotelTransact) transact;
                break;
            }
        }

        return hotelTransact;
    }

    public boolean isReservationRoomNumExist(ArrayList<Reservation> reservations, int roomNum) {
        boolean isExist = false;

        for(int i = 0; i< reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if(reservation.getRoom()!=null) {
                if (reservation.getRoom().getRoomNum()==roomNum) {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    public ArrayList<Reservation> getReservations(ArrayList<ReserveTransact> reserveTransacts) {
        ArrayList<Reservation> reservationsList = new ArrayList<>();

        for(int i = 0; i<reserveTransacts.size(); i++) {
            ReserveTransact reserveTransact = reserveTransacts.get(i);
            Reservation reservation = reserveTransact.getReservation();
            reservationsList.add(reservation);
        }
        return reservationsList;
    }

    public boolean isReservationAmenityCodeExist(ArrayList<Reservation> reservations, String amenityCode) {
        boolean isExist = false;

        for(int i = 0; i< reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if(reservation.getAmenity()!=null) {
                if (reservation.getAmenity().getAmenityCode().equalsIgnoreCase(amenityCode)) {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    public double computeBills(Reservation reservation) {       //This is for reservation
        double bills = 0;
        double rsrvCost = 0;
        if(reservation.getRoom()!=null) {   //The reserve is room
           rsrvCost = reservation.getRoom().getReservationPrice();
        }else if(reservation.getAmenity()!=null) {      //The reserved is amenity
            rsrvCost = reservation.getAmenity().getReservationCost();
        }

        rsrvCost *= reservation.getDuration();
        bills = rsrvCost;

        return bills;
    }

    public HotelTransact paymentProcess(Room room, int duration) {
        HotelTransact transact = null;
        double bills = room.getRatePerDay()*duration;
        double cash = 0;
        boolean successTransact = false;

        while(true) {
            System.out.println("\n=====RECEIPT=====");
            bills = room.getRatePerDay() * duration;
            System.out.println("Room: " + room.getRoomType() + " #" + room.getRoomNum());
            System.out.println("Rate per day: " + room.getRatePerDay());
            System.out.println("Duration(days): " + duration);
            System.out.println("Total bills: " + room.getRatePerDay() + " x " + duration + "days = " + bills);

            boolean isCash = true;
            while(isCash==true) {
                System.out.print("\nEnter cash: ");
                cash = inputDouble();
                if (cash == -1) {
                    System.out.println("INVALID: Input real numbers only!");
                    continue;
                }

                if(cash==bills) {
                    System.out.println("\nThank you for the exact amount");
                }else if(cash>bills) {      //If there is a change
                    double change = cash-bills;
                    System.out.println("\nChange: " + change);
                    while(true) {
                        System.out.print("Press 1 to accept change: " + change + "\n-");
                        pick = inputInt();
                        if(pick==1) {
                            System.out.println("You accepted your change: " + change);
                            break;
                        }else {
                            System.out.println("INVALID: Use indicated number only!");
                        }
                    }
                }else {
                    System.out.println("\nInsufficicent amount of cash! ");
                    continue;
                }
                successTransact = true;
                break;
            }//End of isCash loop

            Date dateTrans = new Date(Main.globalDate);
            if(successTransact==true) transact = new HotelTransact(dateTrans, CustomerPage.customerAcct, room, dateTrans, 0, duration, bills);
            transact.setIsPaid(true);
            Main.sales.add(transact);
            break;
        }//End of main loop
        return transact;
    }

    public boolean paymentProcess(HotelTransact inHotelOrder) {
        boolean isPaid = false;
        double bills = inHotelOrder.getBills();
        System.out.print("Current bills: " + bills);
        double cash = 0;
        boolean successTransact = false;

        while(true) {
            System.out.println("\n=====RECEIPT=====");

            System.out.println("Hotel transact: ");

            ArrayList<Menu> menuOrder = inHotelOrder.getMenuOrdered();
            for(int i = 0; i<menuOrder.size(); i++) {
                Menu menu = menuOrder.get(i);
                System.out.println("Menu: " + menu.getMenuName() + " || Cost: " + menu.getTotalPrice());
            }
            System.out.println("Total bills: " + inHotelOrder.getBills());

            boolean isCash = true;
            while(isCash==true) {
                System.out.print("\nEnter cash: ");
                cash = inputDouble();
                if (cash == -1) {
                    System.out.println("INVALID: Input real numbers only!");
                    continue;
                }

                if(cash==bills) {
                    System.out.println("\nThank you for the exact amount");
                }else if(cash>bills) {
                    double change = cash-bills;
                    System.out.println("\nChange: " + change);
                    while(true) {
                        System.out.print("Press 1 to accept change: " + change + "\n-");
                        pick = inputInt();
                        if(pick==1) {
                            System.out.println("You accepted your change: " + change);
                            break;
                        }else {
                            System.out.println("INVALID: Use indicated number only!");
                        }
                    }
                }else {
                    System.out.println("\nInsufficicent amount of cash! ");
                    continue;
                }
                inHotelOrder.setIsPaid(true);
                isPaid = true;
                inHotelOrder.setDateOfTrans(new Date(inHotelOrder.getDateOfTrans()));
                HotelTransact inHotel = new HotelTransact(inHotelOrder, inHotelOrder.getDuration());
                Main.sales.add(inHotel);
                break;
            }//End of isCash loop
            break;
        }//End of main loop
        return isPaid;
    }

    public Transact paymentProcess(Reservation reservation) {
        Transact transact = null;
        double bills = computeBills(reservation);
        double cash = 0;
        double rsrvCost = 0;
        boolean successTransact = false;

        System.out.println("\n=====RECEIPT=====");
        if(reservation.getRoom()!=null) {       //Reserved is room
            Room room = reservation.getRoom();
            System.out.println("Room: " + room.getRoomType() + " #" + room.getRoomNum());
            System.out.println("Reservation cost: " + room.getReservationPrice());
            rsrvCost = room.getReservationPrice();
        } else if(reservation.getAmenity()!=null) {     //Reserved is amenity
            Amenity amenity = reservation.getAmenity();
            System.out.println("Amenity: " + amenity.getAmenityType() + " " + amenity.getAmenityCode());
            System.out.println("Rate per day: " + amenity.getReservationCost());
            rsrvCost = amenity.getReservationCost();
        }

        System.out.println("Duration(days): " + reservation.getDuration());
        System.out.println("Total bills: " + rsrvCost + " x " + reservation.getDuration() + "days = " + bills);

        while(true) {
            System.out.print("\nEnter cash: ");
            cash = inputDouble();
            if (cash == -1) {
                System.out.println("INVALID: Input real numbers only!");
            }

            if(cash==bills) {
                System.out.println("\nThank you for the exact amount");
            }else if(cash>bills) {
                double change = cash-bills;
                System.out.println("\nChange: " + change);
                while(true) {
                    System.out.print("Press 1 to accept change: " + change + "\n-");
                    pick = inputInt();
                    if(pick==1) {
                        System.out.println("You accepted your change: " + change);
                        break;
                    }else {
                        System.out.println("INVALID: Use indicated number only!");
                    }
                }
            }else {
                System.out.println("\nInsufficicent amount of cash! ");
                continue;
            }
            successTransact = true;
            break;
        }

        //Save the transaction info
        if(successTransact==true) {
            Date  dateTrans = new Date(Main.globalDate);
            transact = new ReserveTransact(dateTrans, CustomerPage.customerAcct, reservation.getStartDate(), bills, reservation);
            transact.setIsPaid(true);
            Main.reserveSales.add(transact);
        }

        return transact;
    }

    public int findWeek(int d) {
        int week = 0;
        if(d>0 && d<=7) week = 1;
        else if(d>7 && d<=14) week = 2;
        else if(d>14 && d<=21) week = 3;
        else if(d>21 && d<=31) week = 4;
        else week = 0;

        return week;
    }

    public double getStaffSales(Staff staff) {      //Returns the sales of the staff
        double sale = 0;
        ArrayList<Transact> saleTransacts = staff.getSales();
        for(int i = 0; i< saleTransacts.size(); i++) {      //Get the sale transact
            Transact transact = saleTransacts.get(i);
            sale+=transact.getBills();
        }
        return sale;
    }

    public ArrayList<Transact> sortTransact(ArrayList<Transact> transacts) {
        ArrayList<Transact> sortTransact = transacts;
        for(int i = 0; i<sortTransact.size(); i++) {
            for(int k = i+1; k< sortTransact.size(); k++) {
                Transact transact = sortTransact.get(i);
                Transact transact2 = sortTransact.get(k);
                if(compareDate(transact.getDateOfTrans(), transact2.getDateOfTrans())==1) { //If date1 is greater than date2 then change
                    Transact temp = new Transact(transact);
                    sortTransact.set(i, transact2);
                    sortTransact.set(k, temp);
                }
            }//End of inner for loop
        }//End of outer for loop
        return sortTransact;
    }

    public Transact transactDuplicateDateCheck(ArrayList<Transact> transacts, Transact transact) {
        Transact transactDup = null;

        for(int i = 0; i<transacts.size(); i++) {
            Transact transact1 = transacts.get(i);
            if(compareDate(transact1.getDateOfTrans(), transact.getDateOfTrans())==0) { //Same date in
                transactDup=transact1;
                break;
            }
        }

        return transactDup;
    }

    public int transactDuplicateDateCheckInt(ArrayList<Transact> transacts, Transact transact) {
        int index = -1;

        for(int i = 0; i<transacts.size(); i++) {
            Transact transact1 = transacts.get(i);
            if(compareDate(transact1.getDateOfTrans(), transact.getDateOfTrans())==0) { //Same date in
                index=i;
                break;
            }
        }

        return index;
    }

    public void displayDailyTransact(ArrayList<Transact> transacts) {
        transacts = sortTransact(transacts);
        ArrayList<Transact> dailyTransacts = new ArrayList<>();     //Store here the already displayed date

        System.out.println("\n=====DAILY-SALES=====");

        //Merging of transacts by daily
        if(transacts.size() != 0) {
            for (int i = 0; i < transacts.size(); i++) {                               //Iterate the transacts
                Transact transact = new Transact(transacts.get(i));                    //Get the bill object
                double bills = transact.getBills();                                    //Save first the bill
                Date dateOfTrans = transact.getDateOfTrans();                          //Date of transaction
                boolean haveDup = false;                                               //If date have duplicate date in transacts
                boolean haveDup2 = false;                                              //If have duplicate in dailyTransacts

                Transact dailyTransact = null;
                int dailyTransactIndex = -1;
                //Check if already in dailyTransact
                if(dailyTransacts.size()!=0) {
                    for (int j = 0; j < dailyTransacts.size(); j++) {
                        Transact transact1 = dailyTransacts.get(j);
                        if(compareDate(transact.getDateOfTrans(), transact1.getDateOfTrans())==0) {     //Check dailyTransact(i) and transact(i) if there is same date
                            haveDup = true;
                            dailyTransact = new Transact(transact1);
                            dailyTransactIndex = j;
                            break;
                        }
                    }
                }

                //Search for transact with same date
                if (transacts.size()>=2) {
                    for (int j = i + 1; j < transacts.size(); j++) {         //Search for duplicate date and save its bill
                        Transact transact2 = transacts.get(j);
                        if (compareDate(transact.getDateOfTrans(), transact2.getDateOfTrans()) == 0) {
                            if(haveDup==true) {bills += transact2.getBills();
                                haveDup2 = true;}
                        }
                    }
                }

                if(haveDup==true) {                         //If there is duplicate in
                    bills+=dailyTransact.getBills();                            //Add the bills in dailyTransact to bills to create new transact
                    Transact transactNew = new Transact(dateOfTrans, bills);
                    dailyTransacts.set(dailyTransactIndex, transactNew);
                }else dailyTransacts.add(new Transact(dateOfTrans, bills));
            }

            dailyTransacts = sortTransact(dailyTransacts);

            //Display the dailyTransacts
            for (Transact dailySales : dailyTransacts) {
                System.out.print("Date: ");
                dailySales.getDateOfTrans().displayDate2();
                System.out.println(" || Bills: " + dailySales.getBills());
            }

        } else {
            System.out.println("\nThere is no sales for this staff");
        }
    }

    public void displayWeeklySales(ArrayList<Transact> transacts) {

        transacts = sortTransact(transacts);
        ArrayList<Transact> weeklyTransacts = new ArrayList<>();

        //Merge the transacts with same date
        for(int i = 0; i<transacts.size(); i++) {
            Transact transact = new Transact(transacts.get(i));
            Date date = new Date(transact.getDateOfTrans());
            int j = findWeek(date.getDate());

            //Assign the date in assigned value date, week1 = 1, week2 = 8, week = 15, week = 22
            Transact transactDup = null;
            int indexDup = 0;
            if (weeklyTransacts.size()>0) {
                for (int k = 0; k < weeklyTransacts.size(); k++) {
                    Transact transact1 = weeklyTransacts.get(k);
                    Date date1  = transact1.getDateOfTrans();
                    int week1 = findWeek(date.getDate());
                    int week2 = findWeek(date1.getDate());
                    if(week1==week2 && date.getMonth()==date1.getMonth() && date.getYear()==date.getYear()) {
                        transactDup = transact1;
                        indexDup = 0;
                    }
                }
            }

            if(transactDup!=null) {
                Transact transaction = new Transact(transactDup.getDateOfTrans(), (transactDup.getBills()+transact.getBills()));
                weeklyTransacts.set(indexDup, transaction);
            }else {
                Transact transact1 = new Transact(date, transact.getBills());
                weeklyTransacts.add(transact1);
            }
        }//End of merge

        weeklyTransacts = sortTransact(weeklyTransacts);

        //Display weekly transacts
        int month = 0;
        int year = 0;
        System.out.println("\n=====WEEKLY-SALES=====");
        if(weeklyTransacts.size()==0) System.out.println("There is no sales");

        for(int i = 0; i<weeklyTransacts.size(); i++) {
            Transact transact = weeklyTransacts.get(i);
            Date date = transact.getDateOfTrans();
            int month1 = date.getMonth();
            int year1 = date.getYear();

            if(month!=month1 || year!=year1) {
                month = month1;
                year = year1;
                System.out.print(Date.monthValue(month1) + " ");
                System.out.println(date.getYear());
            }

            System.out.print("Week: ");
            if(date.getDate()==1) System.out.print("1");
            else if(date.getDate()==8) System.out.print("2");
            else if(date.getDate()==15) System.out.print("3");
            else if(date.getDate()==22) System.out.print("4");

            System.out.println(" Sales: " + transact.getBills());

        }
    }

    public void displayMonthlySales(ArrayList<Transact> transacts) {

        transacts = sortTransact(transacts);
        ArrayList<Transact> monthTransacts = new ArrayList<>();

        //Merge the transacts with same month
        for(int i = 0; i<transacts.size(); i++) {
            Transact transact = new Transact(transacts.get(i));
            Date date = transact.getDateOfTrans();

            //Check the transact if there is already an existing transact in the convergeWeekly
            Transact transactDup = null;
            int indexDup = 0;
            if (monthTransacts.size()>0) {
                for (int k = 0; k < monthTransacts.size(); k++) {
                    Transact transact1 = monthTransacts.get(k);
                    Date date1  = transact1.getDateOfTrans();
                    if(date.getMonth()==date1.getMonth() && date.getYear()==date.getYear()) {
                        transactDup = transact1;
                        indexDup = 0;
                    }
                }
            }

            if(transactDup!=null) {
                Transact transaction = new Transact(transactDup.getDateOfTrans(), (transactDup.getBills()+transact.getBills()));
                monthTransacts.set(indexDup, transaction);
            }else {
                Transact transact1 = new Transact(date, transact.getBills());
                monthTransacts.add(transact1);
            }
        }//End of merge

        monthTransacts = sortTransact(monthTransacts);

        //Display weekly transacts
        int month = 0;
        int year = 0;
        System.out.println("\n=====MONTHLY-SALES=====");
        if(monthTransacts.size()==0) {      //If there is no sales
            System.out.println("There is no sales");
            return;
        }
        for(int i = 0; i<monthTransacts.size(); i++) {
            Transact transact = monthTransacts.get(i);
            Date date = transact.getDateOfTrans();
            int year1 = date.getYear();

            if(year!=year1) {
                year = year1;
                System.out.println("Year " + date.getYear() + " ");
            }

            System.out.print(Date.monthValue(date.getMonth()));
            System.out.println("\tSales: " + transact.getBills());

        }
    }

    public String inputString() {
        String str = sc.nextLine();
        return str;
    }

}

