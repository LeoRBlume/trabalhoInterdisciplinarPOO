#Bibliotecas utilizadas
import pandas as pd
import math
import sys
import getpass

#Definindo os caminhos 
#Arquivo com lista de nomes
nomes = "C:\\Users\\" + getpass.getuser() + "\\nomes.csv"
#Arquivo exportável
caminho = "C:\\Users\\" + getpass.getuser() + "\\resultNaive.txt"

#vetores 1 e 2 armazenam as letras do alfabeto, e os caracteres do começo, meio, penúltimo e último.
vetor1 = [[0 for _ in range(5)] for _ in range(26)]
vetor2 = [[0 for _ in range(5)] for _ in range(26)]
#vetores 3 e 4 armazenam os dataframes para os nomes masculinos e femininos, e seus respectivos índices de sensibilidade.
vetor3 = [[0 for _ in range(4)] for _ in range(26)]
vetor4 = [[0 for _ in range(4)] for _ in range(26)]
#leitura do dataframe
data = pd.read_csv(nomes, sep=',')
df = pd.DataFrame(data)
#organização do dataframe
df.sort_values(by = ['Gênero', 'Nome'])


#Função que recebe uma string e devolve o caractere do meio
def get_middle(string):

    middle = string[len(string)//2]
    return(middle)

#Função que verifica todos os nomes masculinos e envia para o vetor1, respectivamente o caractere final, o primeiro o do meio e o penúltimo.
def masc ():
  for x in range(df.shape[0]):
    if df.loc[x][1] == "M":
      first_char = df.loc[x][0][0].lower()
      middle_char = get_middle(df.loc[x][0])
      last_char = df.loc[x][0][-1]
      penultimate = df.loc[x][0][-2]
   
      for y in range (26):
        if last_char == (vetor1[y][0]):
          vetor1[y][1] += 1
        if first_char == (vetor1[y][0]):
          vetor1[y][2] += 1
        if middle_char == (vetor1[y][0]):
          vetor1[y][3] += 1
        if penultimate == (vetor1[y][0]):
          vetor1[y][4] += 1
    
  #cria o data frame para os nomes femininos com as colunas citadas abaixo.
  masculinos = pd.DataFrame(vetor1,columns=["letra","last","first","middle","penultimate"]) 
  return(masculinos)

#Função que verifica todos os nomes femininos e envia para o vetor1, respectivamente o caractere final, o primeiro o do meio e o penúltimo.
def fem ():
  for x in range(df.shape[0]):
     if df.loc[x][1] == "F":
       first_char = df.loc[x][0][0].lower()
       middle_char = get_middle(df.loc[x][0])
       last_char  = df.loc[x][0][-1]
       penultimate = df.loc[x][0][-2]
     

       for y in range (26):
        if last_char == (vetor2[y][0]):
            vetor2[y][1] += 1
        if first_char == (vetor2[y][0]):
            vetor2[y][2] += 1
        if middle_char == (vetor2[y][0]):
            vetor2[y][3] += 1
        if penultimate == (vetor2[y][0]):
            vetor2[y][4] += 1

 
  #cria o data frame para os nomes femininos com as colunas citadas abaixo.
  femininos = pd.DataFrame(vetor2,columns=["letra","last","first","middle","penultimate"]) 
  # axf = femininos.plot.bar(x= 0, y= 1 , rot=0)   
  return(femininos)

#função que além de informar os caracteres do alfabeto para o vetor 1 e 2, e junta ambos os data frames criados nas funções 
def calculo():
    for i in range(97, 123):
        vetor1[i - 97][0] = "{:c}".format(i)
        vetor1[i-97][1] = 0
        vetor2[i - 97][0] = "{:c}".format(i)
        vetor2[i-97][1] = 0
    
    dados = [masc(),fem()]
    dados[0] = dados[0].replace([0],1)
    dados[1] = dados[1].replace([0],1)
    return(dados)

def plot():
    #Função de debug, que plota o grafico em barra que consiste em a quantidade de letras em cada posição mencionada nas funções
    # acima, contendo ambos os dataframes sobrepostos.
    dados = calculo()  
    dados[0]['Key'] = 'masc'
    dados[1]['Key'] = 'fem'
    DF = pd.concat([dados[0],dados[1]],keys=['masc','fem'])
    DFGroup = DF.groupby(['letra','Key'])
    DFGPlot = DFGroup.sum().unstack('Key').plot(kind='bar',title="Nomes terminados em:") 

def NaiveBayes():
    dados = calculo()

    tM = 0
    tF = 0
    #loop, que faz a contagem total de nomes masculinos
    for x in range(df.shape[0]):
        if df.loc[x][1] == "M":
            tM += 1
        else:
            tF += 1
    
    for z in range (26):
        #Faz o cálculo do índice de incidência dos nomes masculinos para todas as características mencionadas.
        #ultima letra
        vetor3[z][0] = round((dados[0].loc[z][1]/dados[0]['last'].sum()),4)
        #primeira letra
        vetor3[z][1] = round((dados[0].loc[z][2]/dados[0]['first'].sum() ),4)
        #letra do meio
        vetor3[z][2] = round((dados[0].loc[z][3]/dados[0]['middle'].sum() ),4)
        #penultima letra
        vetor3[z][3] = round((dados[0].loc[z][4]/dados[0]['penultimate'].sum()),4)
    

        #Faz o cálculo do índice de incidência dos nomes femininos para todas as características mencionadas.

        #ultima letra
        vetor4[z][0] = round((dados[1].loc[z][1]/dados[1]['last'].sum()),4)
        #primeira letra
        vetor4[z][1] = round((dados[1].loc[z][2]/dados[1]['first'].sum() ),4)
        #letra do meio
        vetor4[z][2] = round((dados[1].loc[z][3]/dados[1]['middle'].sum() ),4)
        #penultima letra
        vetor4[z][3] = round((dados[1].loc[z][4]/dados[1]['penultimate'].sum() ),4)
    
    
    #Cálculo Índice de precisão para nomes masculinos e femininos
    pM =  round(tM/(tM + tF),4)
    pF =  round(tF/(tM + tF),4) 

    
    #envia todas as informações para o array Bayes.
    Bayes = [pM,pF,vetor3,vetor4]
    return Bayes 

valores  = NaiveBayes()
#Abre ou cria o arquivo resultNaive.txt para escrita de texto.
arquivo = open(caminho, 'w');

#flag para ignorar o sys.argv[0] (primeiro parâmetro)
flag = False
#Loop for, para inserir enquanto houver parâmetros como argumento.
for param in sys.argv :
    #recebe o parametro dos sys.argv (input do console) e coloca na variavel name
    name = param.lower()
    #respectivamente, coleta o primeiro caractere, o do meio, o último e o penúltimo.
    first_char = name[0]
    middle_char = get_middle(name)
    last_char = name[-1]
    penultimate = name[-2]
    total_masculino = 0
    total_feminino  = 0


    for i in range(97, 123):
        #procura os caracteres coletados acima e atribui seu valor do índice de incidência para as variaveis criadas.
            if first_char == "{:c}".format(i):
                masculino_first = (valores[2][i-97][1])
                feminino_first =  (valores[3][i-97][1])
    

            if middle_char == "{:c}".format(i):
                masculino_middle = (valores[2][i-97][2])
                feminino_middle =  (valores[3][i-97][2])
          

            if penultimate == "{:c}".format(i):
                masculino_penultimate = (valores[2][i-97][3])
                feminino_penultimate =  (valores[3][i-97][3])

            
            if last_char == "{:c}".format(i):
                masculino_last = (valores[2][i-97][0])
                feminino_last  =  (valores[3][i-97][0])    
            
            
         
    
    #Calcula a precisão para masculino e feminino
    total_masculino = (valores[0]*masculino_first *masculino_middle*masculino_penultimate * masculino_last)
    total_feminino  = (valores[1]*feminino_first  *feminino_middle*feminino_penultimate*feminino_last)


    #adiciona a base logarítmica
    total_masculino = math.log(total_masculino,10) 
    total_feminino  = math.log(total_feminino,10) 

    #Compara os valores e prediz o resultado.
    if total_masculino > total_feminino:
        result = "Masculino"

    else:
        result = "Feminino" 
    
            
    total_masculino = round(total_masculino,4)
    total_feminino = round(total_feminino,4)
    
    if(flag):
        #Escreve no arquivo
        arquivo.writelines(result+" "+(str(total_masculino)+ " " + (str(total_feminino)+"\n"))) 
            
    flag = True    
        
arquivo.close();

