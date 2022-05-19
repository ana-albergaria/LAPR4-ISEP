grammar questionnaire ;

//survey :

//id -> mandatory alphanumeric value to univocally identify a questionnaire (E.g.: "COSM22-01")
id : alfas ;

alfas : alfa alfas
      | alfa ;

letra : MINUSCULA | MAIUSCULA ;

palavra: (letra)+;

alfa : palavra | DIGITO ;

texto: (DIGITO)* palavra (DIGITO)*
       | DIGITO;

frase :  texto frase
      | texto ;

//title: it is a mandatory short sentence (nota: ver se exite limite de caracteres)
title : frase ;

message : frase
        | (frase NEWLINE)+ ;

/* SECÇÃO */
id_numerico : (DIGITO)+ ;

obligatoriness : MANDATORY | OPTIONAL | CONDITION_DEPENDENT ;

//falta repeatability

/*content : questao questoes
        | questao ;*/

//seccao :



/* QUESTÃO */

question_text : frase PONTO_INTERROGACAO
            | frase PONTO_EXCLAMACAO;

type: 'free text'
    | 'numeric'
    | 'single-choice' single_choice
    | 'multiple_choice' multiple_choice
    | 'single-choice with input' single_choice_input
    | 'multiple-choice with input' multiple_choice_input
    | 'sorting options' sorting_option
    | 'scaling options' scaling_option;

question_list: frase NEWLINE question_list
            | frase;

single_choice: question_list;
single_choice_input: question_list;
multiple_choice: question_list;
multiple_choice_input: question_list;
sorting_option: question_list;
scaling_option: question_list;







MAIUSCULA : [A-Z] ;
MINUSCULA : [a-z] ;
//PALAVRA : [A-Za-z]+ ;
DIGITO : [0-9] ;
PONTO_INTERROGACAO : '?' ;
PONTO_EXCLAMACAO: '!';
MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';
NEWLINE: '\n' ;             //return end of line
WS : [ \t\r.,?!*'-]+ -> skip ; //skip spaces, tabs, newlines


