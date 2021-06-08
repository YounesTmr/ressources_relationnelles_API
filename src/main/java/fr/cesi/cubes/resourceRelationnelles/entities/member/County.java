package fr.cesi.cubes.resourceRelationnelles.entities.member;

public enum County {

    AIN ( "01 - AIN" ),
    AISNE ( "02 - AISNE" ),
    ALLIER ( "03 - ALLIER" ),
    ALPES_DE_HAUTE_PROVENCE ( "04 - ALPES-DE-HAUTE-PROVENCE" ),
    HAUTES_ALPES ( "05 - HAUTES-ALPES" ),
    ALPES_MARITIMES ( "06 - ALPES-MARITIMES" ),
    ARDECHE ( "07 - ARDÈCHE" ),
    ARDENNES ( "08 - ARDENNES" ),
    ARIEGE ( "09 - ARIÈGE" ),
    AUBE ( "10 - AUBE" ),
    AUDE ( "11 - AUDE" ),
    AVEYRON ( "12 - AVEYRON" ),
    BOUCHES_DU_RHONE ( "13 - BOUCHES-DU-RHÔNE" ),
    CALVADOS ( "14 - CALVADOS" ),
    CANTAL ( "15 - CANTAL" ),
    CHARENTE ( "16 - CHARENTE" ),
    CHARENTE_MARITIME ( "17 - CHARENTE-MARITIME" ),
    CHER ( "18 - CHER" ),
    CORREZE ( "19 - CORRÈZE" ),
    CORSE_DU_SUD ( "2A - CORSE-DU-SUD" ),
    HAUTE_CORSE ( "2B - HAUTE-CORSE" ),
    COTE_D_OR ( "21 - CÔTE-D'OR" ),
    COTES_D_ARMOR ( "22 - CÔTES D'ARMOR" ),
    CREUSE ( "23 - CREUSE" ),
    DORDOGNE ( "24 - DORDOGNE" ),
    DOUBS ( "25 - DOUBS" ),
    DROME ( "26 - DRÔME" ),
    EURE ( "27 - EURE" ),
    EURE_ET_LOIR ( "28 - EURE-ET-LOIR" ),
    FINISTERE ( "29 - FINISTÈRE" ),
    GARD ( "30 - GARD" ),
    HAUTE_GARONNE ( "31 - HAUTE-GARONNE" ),
    GERS ( "32 - GERS" ),
    GIRONDE ( "33 - GIRONDE" ),
    HERAULT ( "34 - HÉRAULT" ),
    ILLE_ET_VILAINE ( "35 - ILLE-ET-VILAINE" ),
    INDRE ( "36 - INDRE" ),
    INDRE_ET_LOIRE ( "37 - INDRE-ET-LOIRE" ),
    ISERE ( "38 - ISÈRE" ),
    JURA ( "39 - JURA" ),
    LANDES ( "40 - LANDES" ),
    LOIR_ET_CHER ( "41 - LOIR-ET-CHER" ),
    LOIRE ( "42 - LOIRE" ),
    HAUTE_LOIRE ( "43 - HAUTE-LOIRE" ),
    LOIRE_ATLANTIQUE ( "44 - LOIRE-ATLANTIQUE" ),
    LOIRET ( "45 - LOIRET" ),
    LOT ( "46 - LOT" ),
    LOT_ET_GARONNE ( "47 - LOT-ET-GARONNE" ),
    LOZERE ( "48 - LOZÈRE" ),
    MAINE_ET_LOIRE ( "49 - MAINE-ET-LOIRE" ),
    MANCHE ( "50 - MANCHE" ),
    MARNE ( "51 - MARNE" ),
    HAUTE_MARNE ( "52 - HAUTE-MARNE" ),
    MAYENNE ( "53 - MAYENNE" ),
    MEURTHE_ET_MOSELLE ( "54 - MEURTHE-ET-MOSELLE" ),
    MEUSE ( "55 - MEUSE" ),
    MORBIHAN ( "56 - MORBIHAN" ),
    MOSELLE ( "57 - MOSELLE" ),
    NIÈVRE ( "58 - NIÈVRE" ),
    NORD ( "59 - NORD" ),
    OISE ( "60 - OISE" ),
    ORNE ( "61 - ORNE" ),
    PAS_DE_CALAIS ( "62 - PAS-DE-CALAIS" ),
    PUY_DE_DÔME ( "63 - PUY-DE-DÔME" ),
    PYRENEES_ATLANTIQUES ( "64 - PYRÉNÉES-ATLANTIQUES" ),
    HAUTES_PYRENEES ( "65 - HAUTES-PYRÉNÉES" ),
    PYRENEES_ORIENTALES ( "66 - PYRÉNÉES-ORIENTALES" ),
    BAS_RHIN ( "67 - BAS-RHIN" ),
    HAUT_RHIN ( "68 - HAUT-RHIN" ),
    RHONE ( "69 - RHÔNE" ),
    HAUTE_SAONE ( "70 - HAUTE-SAÔNE" ),
    SAONE_ET_LOIRE ( "71 - SAÔNE-ET-LOIRE" ),
    SARTHE ( "72 - SARTHE" ),
    SAVOIE ( "73 - SAVOIE" ),
    HAUTE_SAVOIE ( "74 - HAUTE-SAVOIE" ),
    PARIS ( "75 - PARIS" ),
    SEINE_MARITIME ( "76 - SEINE-MARITIME" ),
    SEINE_ET_MARNE ( "77 - SEINE-ET-MARNE" ),
    YVELINES ( "78 - YVELINES" ),
    DEUX_SEVRES ( "79 - DEUX-SÈVRES" ),
    SOMME ( "80 - SOMME" ),
    TARN ( "81 - TARN" ),
    TARN_ET_GARONNE ( "82 - TARN-ET-GARONNE" ),
    VAR ( "83 - VAR" ),
    VAUCLUSE ( "84 - VAUCLUSE" ),
    VENDEE ( "85 - VENDÉE" ),
    VIENNE ( "86 - VIENNE" ),
    HAUTE_VIENNE ( "87 - HAUTE-VIENNE" ),
    VOSGES ( "88 - VOSGES" ),
    YONNE ( "89 - YONNE" ),
    TERRITOIRE_DE_BELFORT ( "90 - TERRITOIRE DE BELFORT" ),
    ESSONNE ( "91 - ESSONNE" ),
    HAUTS_DE_SEINE ( "92 - HAUTS-DE-SEINE" ),
    SEINE_ST_DENIS ( "93 - SEINE-SAINT-DENIS" ),
    VAL_DE_MARNE ( "94 - VAL-DE-MARNE" ),
    VAL_D_OISE ( "95 - VAL-D'OISE" ),
    GUADELOUPE ( "971 - GUADELOUPE" ),
    MARTINIQUE ( "972 - MARTINIQUE" ),
    GUYANE ( "973 - GUYANE" ),
    LA_REUNION ( "974 - LA RÉUNION" ),
    MAYOTTE ( "976 - MAYOTTE" );

    private final String name;

    private County( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
