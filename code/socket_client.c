#include"stdio.h"  
#include"stdlib.h"  
#include"sys/types.h"  
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include"string.h"  
#include"netinet/in.h"  
#include"netdb.h"
#include"pthread.h"

//Calculator Socket Client Only

#define PORT 45227  //change this
#define BUF_SIZE 2000 

typedef struct{ //Struct apothikefsis dedomenon isodou
    char select;
    char * a_value;
    char * Y_value;
    char * n_value; 
    int n_length;
    int Y_length; 
    int a_length;
}data_save;

char * keyboard_read(){ // diavasma xaraktiron apo to pliktrologio
    int capacity = 1;
    int size = 0;
    int c;
    char *string = malloc(sizeof(char) * capacity);
    fflush(stdin);
    while((c = fgetc(stdin) )!= '\n'){
        string[size++] = c;
        if (size == capacity) {
            string = realloc ( string ,capacity * sizeof (char));                        
        }
    }
    return string;
}

int num_control(char * string){// elenxos xaraktiron eisodou // epitrepomeni 0-9
    int j_flag = 0;
    for (int i = 0; i < strlen(string); ++i)
    {
        if((string[i] <= '0') || (string[i]>='9')){
            j_flag++;
        }
    }
    if (j_flag == 0) {
        return 0;
    }else{
        return 1;        
    }
}

data_save  menu(){ // sinarti eisodou dedomenon kai anathesi tous stin struct    
    data_save data;
    data.Y_length=0;//arxikopihsi timon
    data.n_length=0;
    data.a_length=0;
    char * local_n, * local_Y, * local_a;
    int n;
    int flag_select_VectorProduct = 0;
    int flag_select_Menu = 1;
    char buffer[BUF_SIZE];
    char * string;
    char * string2;
    int capacity = 1;
    int size = 0;
    int c;    
    while(1){        
        if (flag_select_Menu == 1){// flag epilogis menou
            fflush(stdin);
            fgets(buffer, BUF_SIZE, stdin);// diavasma xaraktira epilogis eisodou
            if (buffer[0] == '1'){
                flag_select_Menu = 0;
                data.select = buffer[0];
                continue;
            } else if (buffer[0] == '2'){// 
                flag_select_Menu = 0;
                data.select = buffer[0];                
                continue;
            } else if (buffer[0] == '3'){
                flag_select_VectorProduct =1;
                flag_select_Menu = 0;
                data.select = buffer[0];
                continue;
            } else if (buffer[0] == '0'){
                data.select = buffer[0];
                break;
            } else{
                printf("Client Wrong Select input\n");
                printf("> ");
                continue;
            }   
        }else if (flag_select_Menu == 0){             
            while(1){ //epanalipsi eisodou gia to megethos tou pinaka Y
                printf("Dose megethos pinaka (n)\n");
                printf("\n> ");
                string = keyboard_read(string);// diavasma megethous pinaka
                if(num_control(string) == 0){// elenxos gia to an o xaraktiras isodou einai apodektos
                    break;
                }else{
                    printf("Edoses mi apodekto xaraktira!!\n");
                }                    
            }            
            data.n_length = strlen(string);//apothikefsi mikous tou n stin struct
            data.n_value = (char *)malloc(sizeof(char)*data.n_length);//desmefsi theseon mnimis analoges me to mikos tou n
            strcpy(data.n_value,string);//anathesi periexomenou pou diavastike stin struct
            printf("n value setup ok..\n");
            free(string);  
            while(1){ 
                printf("Dose Times tou dianismatos (Y) me diaxoristika (,)\n");
                printf("Paradiga: 1,2,3\n");
                printf("\n> "); 
                int capacity = 1;                        
                int size = 0;
                int c;
                int comma_counter = 0;                     
                string = malloc(sizeof(char) * capacity);//desmefsi 1 xaraktira stin mnimi
                fflush(stdin);
                while((c = fgetc(stdin) )!= '\n'){// diavasma enos xaraktira apo to pliktrologio
                    if ( ((c>='0') && (c<='9')) || (c==',') ){//elenxos apodekton xaraktiron
                        if (c == ',') {
                            comma_counter++;//counter metrimatos tou xaraktira ','
                        }
                        string[size++] = c; // apothikefsi tou xaraktira ston pinaka kai metavoli tou size stin epomeni thesi    
                    } else{
                        printf("Edoses mi apodektous xaraktires\n");
                        printf("\n> ");
                        break;
                    }                            
                    if (size == capacity) {// an iparxi parapano apo enas xaraktiras tote kanoume realocate tin mnimi gia na diavasoume ton epomeno
                        string = realloc ( string ,capacity * sizeof (char));                        
                    }
                }
                if (comma_counter == (atoi(data.n_value)-1)) {
                    break;
                } else{
                    printf("Edoses mi apodekto mikos dianismatos (Y)\n");
                }
            }
            if (flag_select_VectorProduct == 1) { // ean exoume epileksi 3 tote diavazoume to kai to a                                             
                while(1){
                    printf("Dose Timi Tou Polaplasiasti (a) gia dekadiko vale (.)\n");
                    printf("\n> "); 
                    capacity = 1;                        
                    size = 0;                
                    int comma_counter = 0;                     
                    string2 = malloc(sizeof(char) * capacity);
                    fflush(stdin);
                    memset(string2,0,capacity);               
                    while((c = fgetc(stdin) )!= '\n'){
                        if( ((c>='0') && (c<='9')) || (c=='.') ){
                            if (c == '.') {
                                comma_counter++;
                            }
                            string2[size++] = c;    
                        }else{
                            printf("Edoses mi apodektous xaraktires\n");
                            printf("\n> ");
                            break;
                        }                            
                        if (size == capacity) {
                            string2 = realloc ( string2 ,capacity * sizeof (char));                        
                        }
                    }
                    if ((comma_counter == 1) || (comma_counter == 0)) {
                        break;
                    }else{
                        printf("Pliktrologises parapano telies apo oses xriazonte (Y)\n");
                    }
                }                
                data.a_length = strlen(string2);//anathetoume mikos kai times tou alfa
                data.a_value =  (char *)malloc(sizeof(char)*data.a_length);
                strcpy(data.a_value,string2);
                printf("%d, %s \n",data.a_length,data.a_value);
            }               
            data.Y_length = strlen(string);// kai anathetoume mikos kai times tou Y
            data.Y_value = (char *)malloc(sizeof(char)*data.Y_length);
            strcpy(data.Y_value,string);
            break;
        }
    }
    return data;   
}

void receiveMessage(void * socket) {// sinartisi diavasmatos dedomenon apo ton socket server kai ektiposi tous stin othoni
    int sockfd, ret;
    char buffer[BUF_SIZE]; 
    sockfd = *((int*)socket);
    memset(buffer, 0, BUF_SIZE);  
    ret = recvfrom(sockfd, buffer, BUF_SIZE, 0, NULL, NULL);  
    if (ret < 0) {  
        printf("Error receiving data!\n");    
    } else {    	
        printf("server: ");
        fputs(buffer, stdout);
        printf("\n\n");     
    }        
}

int main(int argc, char**argv) {
    data_save dt;
    struct sockaddr_in addr;  
    int sockfd, ret;  
    char buffer[BUF_SIZE];
    char * buf; 
    char * serverAddr;

    
    if (argc < 2) {
        printf("usage: client < ip address >\n");
        exit(1);  
    }

    serverAddr = argv[1]; //anathesi tou 127.0.0.1 os diefthinsi server

    sockfd = socket(AF_INET, SOCK_STREAM, 0);  // dimiourgia tcp socket
    if (sockfd < 0) {  
        printf("Error creating socket!\n");  
        exit(1);  
    }  
    printf("Socket created...\n");   

    memset(&addr, 0, sizeof(addr));  
    addr.sin_family = AF_INET;  // anathesi timon sto socket (diefthinsis ports klp)
    addr.sin_addr.s_addr = inet_addr(serverAddr);
    addr.sin_port = PORT;     

    ret = connect(sockfd, (struct sockaddr *) &addr, sizeof(addr));  //sindesi ston socket server
    if (ret < 0) {  
        printf("Error connecting to the server!\n");  
        exit(1);  
    }  
    printf("Connected to the server...\n");  

    memset(buffer, 0, BUF_SIZE);
    printf("Enter your messages one by one and press return key!\n");

    //creating a new thread for receiving messages from the server    
    
    while(1){
        printf("Hello...\n");
        printf("Please Type Command.\n");
        printf("(0)-Exit (1)-Average (2)-Max Min (3)-Vector Product\n");
        printf("\n> ");

        dt = menu(); // klisi tis sinartisis menou kai anathesi tis epistrefomenis struct stin dt
        if (dt.select == '0') {// ean to select einai 0 termatismos tou client kai enimerosi tou server
            buf = (char *)malloc(sizeof(char));
            buf[0]=dt.select;
            ret = sendto(sockfd, buf, strlen(buf), 0, (struct sockaddr *) &addr, sizeof(addr));
            if (ret < 0) {  
                printf("Error sending data!\n\t-%s", buffer);  
            }      
            break;
        }
        printf("\nY_Vector [");
        fputs(dt.Y_value,stdout);
        printf("]\nY_length [%ld",strlen(dt.Y_value));
        printf("]\nn_Vector [");
        fputs(dt.n_value,stdout);    
        printf("]\nn_length [%ld]\n",strlen(dt.n_value));
        printf("Select [%c]\n",dt.select);        
        
        if (dt.select == '1' || dt.select == '2') {// dimiourgia string array tis morfis bu[]=[a;n;Y[0],Y[1],...Y[n]] gia tis epiloges 1 kai dio
            buf = (char *)malloc(sizeof(char)*(dt.n_length+dt.Y_length)+3);
            buf[0]=dt.select;
            strcat(buf,";");
            strcat(buf,dt.n_value);
            strcat(buf,";");
            strcat(buf,dt.Y_value);
        }else if(dt.select == '3'){// dimiourgia string array tis morfis bu[]=[a;n;Y[0],Y[1],...Y[n];a]
            buf = (char *)malloc(sizeof(char)*(dt.n_length+dt.Y_length)+dt.a_length+4);
            buf[0]=dt.select;
            strcat(buf,";");
            strcat(buf,dt.n_value);
            strcat(buf,";");
            strcat(buf,dt.Y_value);
            strcat(buf,";");
            strcat(buf,dt.a_value);    
        }    

        fputs(buf,stdout);
        //sleep(5);
        ret = sendto(sockfd, buf, strlen(buf), 0, (struct sockaddr *) &addr, sizeof(addr));//apostoli tou string ston socket server
        if (ret < 0) {  
            printf("Error sending data!\n\t-%s", buffer);  
        }           
        printf("\n\n\n> ");
        receiveMessage(&sockfd);//klisi tis receiveMessage gia tin ektiposi tou apotelesmatos
        printf("\n\n");
    }
    close(sockfd);
    return 0;
}