# projeto-lp2019

Enunciado do Projeto Final

❖	REDE SOCIAL ALWAYSON

As redes sociais são um fenómeno social e tecnológico. De uma forma muito simples podemos dizer que cada utilizador possui um perfil próprio, com a instanciação de vários atributos, e identifica também um conjunto de outros utilizadores com os quais está de alguma forma relacionado. Existem várias redes sociais que são exemplos do que se acaba de descrever, como por exemplo, o Facebook, o Twitter, Orkut, o LinkedIn, entre outros. No caso vertente do LinkedIn os utilizadores inscrevem-se na rede com o objetivo de criarem uma teia de relacionamentos de índole profissional. Muitos dos utilizadores procuram nesta rede novas oportunidades a nível profissional ao fazerem parte de uma teia relacionada pelos mesmos interesses. Uma das atividades mais frequentes no LinkedIn consiste na procura de contactos que permitam ajudar a promover a carreira profissional de um utilizador. Tipicamente o objetivo é contactar alguém e utilizar a rede social para conseguir alguém que nos apresente (ou recomende).
Assuma que pretendemos criar a nossa rede social que se designará por AlwaysOn. A proposta de valor da AlwaysOn assenta nas seguintes premissas:

•	As relações entre os utilizadores podem ser de três tipos: Profissional, Família e Amigos

•	É possível adicionar novos utilizadores e registar a quem é que estão ligados de acordo com os diferentes tipos de relações

•	É objetivo dos promotores da rede social que esta possa atingir pelo menos os 10000 utilizadores, sendo que o programa deve estar preparado para tal.


Cada utilizador conhece os utilizadores com que está diretamente relacionado. Na altura de criar uma ligação a outro utilizador é necessário escolher o tipo de relacionamento (Profissional, Família e Amigos) e atribuir um peso à distância entre os dois utilizadores. Regra geral a distância entre dois utilizadores é de 1, embora nada impeça que o custo da relação seja superior. Imagine-se que o utilizador X conhece o utilizador Y, mas a relação de confiança entre ambos não é significativa. Nesse caso o custo pode ser superior a 1, na medida em que utilizar Y, para determinado propósito, implica que este está a prestar, de forma algo renitente, um favor. Para cada utilizador é guardada a seguinte informação: nome, cidade onde vive, estado civil, número de contribuinte e email.


❖	TAREFAS

O objetivo principal será criar uma aplicação para gerir o backoffice da rede social. Após a modelação em UML serão feitas tarefas modulares; o desenvolvimento da estrutura de dados do rede social que contém os utilizadores, relações e mensagens dividir-se-á em tarefas:

Tarefa 1: implementação do módulo de gestão de utilizadores

•	Criação da Scene para inserir, editar e remover utilizadores e relacionamentos

Tarefa 2: implementação do módulo de visualização de perfis

•	Criação da Scene para visualizar perfil de utilizador, com pesquisa e seleção

Tarefa 3: implementação do módulo de gestão de mensagens

•	Criação da Scene para inserir, editar e remover mensagens

Tarefa 4: implementação do módulo de pesquisa de utilizadores por distância

•	Criação da Scene para devolver utilizadores consoante a distância d indicada 

Tarefa 5: implementação do módulo de cálculo de distâncias

•	Criação da Scene para determinar qual é a distância máxima entre quaisquer dois utilizadores da rede social e os utilizadores que estão mais afastados entre si

Tarefa 6: implementação do módulo de visualização de popularidade

•	Criação da Scene para calcular os 3 utilizadores mais populares (mais ligações) e com mais mensagens

Tarefa 7: implementação do módulo de pesquisa

•	Criação da Scene para pesquisar mensagens e utilizadores 


O programa deve permitir que em qualquer momento se possa guardar em ficheiro a informação existente em memória sobre os utilizadores, ligações entre eles e mensagens trocadas. A gravação deve ser feita da forma mais otimizada possível e deve permitir que o estado seja recuperado novamente.

O código base está presente em https://github.com/andremont/projeto-lp2019. Os grupos deverão fazer checkout da versão existente e trabalhar dentro da pasta ismt.application.scene na sua tarefa específica. Podem sugerir melhoramentos à estrutura principal a qualquer momento e esperar que o docente as reveja e aceite.


❖	OBJETIVO

Com a realização do Projeto Final pretende-se familiarizar os alunos com o desenvolvimento estruturado de uma aplicação em Java, englobando todas as fases de um projeto de software. Com efeito, os alunos devem ser capazes de compreender e dominar os conceitos e as técnicas fundamentais inerentes à conceção, desenho, implementação, testes, usabilidade e avaliação de aplicações, de complexidade moderada, incluindo competências de modelação da informação e domínio da linguagem Java.
Os alunos são livres de melhorar o programa da forma que entenderem, desde que não o desvirtuem. A qualidade da programação é o ponto essencial. 

A resolução deverá cumprir os seguintes requisitos:

o	Estruturação apropriada, i.e., modularização apropriada em métodos (funções e procedimentos);

o	Nomes de entidades (variáveis, constantes e módulos) apropriados;

o	Todas as opções importantes e/ou extras do programa deverão ser explicados no cabeçalho do programa.


Todas as fases do projeto serão alvo de avaliação pelo que se pretende que sejam executadas. A saber:

1.	Modelação do projeto em UML

2.	Geração do código de classes a partir do UML

3.	Realização de testes unitários sobre as classes e respetivos métodos

4.	Utilização de padrões de programação

5.	Implementação de uma interface gráfica GUI

6.	Colocação do código num repositório GitHub

7.	Refatoração do código

Os casos omissos neste enunciado deverão ser esclarecidos junto do docente da unidade curricular.


❖	AVALIAÇÃO

O trabalho será avaliado nas seguintes vertentes:

●	Clareza do código

●	Adequação da estrutura de dados

●	Adequação da estrutura de controlo

●	Funcionamento

●	Execução de cada uma das fases indicadas no ponto anterior


❖	ENTREGA

O projeto final deve ser entregue até ao dia 12-06-2019 para o repositório GitHub, na tarefa específica Projeto Final; em alternativa poderá ser entregue num único ficheiro Zip. Após a data limite de entrega não será aceite qualquer projeto ou partes do mesmo.


❖	ALGUMAS NOTAS

- Não serão admitidos plágios – trabalhos copiados terão nota ZERO (tanto quem copiou como quem deixou copiar).

- A funcionalidade do programa é muito mais importantes do que os aspetos estéticos.
