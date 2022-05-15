grammar questionnaire ;

//survey :

//id -> mandatory alphanumeric value to univocally identify a questionnaire (E.g.: "COSM22-01")
id : alfas ;

alfas : alfa (HIFEN)? alfas
      | alfa ;

letra : MINUSCULA | MAIUSCULA ;

alfa : letra | DIGITO ;


frase : PALAVRA frase
      | PALAVRA ;

//title: it is a mandatory short sentence (nota: ver se exite limite de caracteres)
title : frase ;

welcome_message : frase
                | (frase NEWLINE)+ ;

/* SECÇÃO */
id_numerico : (DIGITO)+ ;

obligatoriness : MANDATORY | OPTIONAL | CONDITION_DEPENDENT ;

//falta repeatability

/*content : questao questoes
        | questao ;*/

//seccao :



/* QUESTÃO */

question_text : frase PONTO_INTERROGACAO ;

//







MAIUSCULA : [A-Z] ;
MINUSCULA : [a-z] ;
PALAVRA : [A-Za-z]+ ;
DIGITO : [0-9] ;
HIFEN : '-' ;
PONTO_INTERROGACAO : '?' ;
MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';
NEWLINE: '\n' ;             //return end of line
WS : [ \t\r.,?!*']+ -> skip ; //skip spaces, tabs, newlines


