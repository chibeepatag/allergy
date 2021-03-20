package com.chibee.allergy.utilities

import com.chibee.allergy.data.Drug
import com.chibee.allergy.data.Reaction

/**
 * Constants used throughout the app.
 */
const val DATABASE_NAME = "allergy-db"
const val DRUG_DATA_FILENAME = "drug.json"
const val REACTION_DATA_FILENAME = "reaction.json"



class DBSeed() {
    companion object {
        fun getDrugs(): List<Drug>{
            val drugs = ArrayList<Drug>()
            drugs.add(Drug(category = "Penicillins", name = "Penicillin G"))
            drugs.add(Drug(category = "Penicillins",        name =  "Ampicillin"))
            drugs.add(Drug(category = "Penicillins",        name =  "Amoxicillin"))
            drugs.add(Drug(category = "Penicillins",        name =  "Amoxicillin-clavulanate"))
            drugs.add(Drug(category = "Penicillins",        name =  "Ampicillin-sulbactam"))
            drugs.add(Drug(category = "Penicillins",        name =  "Piperacillin-tazobactam"))
            drugs.add(Drug(category = "Cephalosporins",     name =  "Cefazolin"))
            drugs.add(Drug(category = "Cephalosporins",     name =  "Ceftriaxone"))
            drugs.add(Drug(category = "Cephalosporins",     name =  "Ceftazidime"))
            drugs.add(Drug(category = "Cephalosporins",     name =  "Cefepime"))
            drugs.add(Drug(category = "Cephalosporins",     name =  "Ceftaroline"))
            drugs.add(Drug(category = "Tetracyclines",      name =  "Doxycycline"))
            drugs.add(Drug(category = "Tetracyclines",      name =  "Minocycline"))
            drugs.add(Drug(category = "Tetracyclines",      name =  "Tigecycline"))
            drugs.add(Drug(category = "Macrolides",         name =  "Erythromycin"))
            drugs.add(Drug(category = "Macrolides",         name =  "Azithromycin"))
            drugs.add(Drug(category = "Macrolides",         name =  "Clarithromycin"))
            drugs.add(Drug(category = "Glycopeptides",      name =  "Vancomycin"))
            drugs.add(Drug(category = "Cyclic Lipopeptide", name =  "Daptomycin"))
            drugs.add(Drug(category = "Oxazolidinone" ,     name =  "Linezolid"))
            drugs.add(Drug(category = "Lincosamide",        name =  "Clindamycin"))
            drugs.add(Drug(category = "Sulfonamides",       name =  "Trimethoprim-sulfamethoxazole"))
            drugs.add(Drug(category = "Nitroimidazole",     name =  "Metronidazole"))
            drugs.add(Drug(category = "Aminoglycosides",    name =  "Gentamicin"))
            drugs.add(Drug(category = "Aminoglycosides",    name =  "Tobramycin"))
            drugs.add(Drug(category = "Aminoglycosides",    name =  "Amikacin"))
            drugs.add(Drug(category = "Nitrofurans",        name =  "Nitrofurantoin"))
            return drugs
        }

        fun getReactions(): List<Reaction>{
            val reactions = ArrayList<Reaction>()
            reactions.add(Reaction(system = "", reaction =  "Anaphylaxis"))
            reactions.add(Reaction(system = "Skin", reaction =  "Rash"))
            reactions.add(Reaction(system = "Skin", reaction =  "Urticaria/Hives/Welts"))
            reactions.add(Reaction(system = "Skin", reaction =  "Jaundice"))
            reactions.add(Reaction(system = "Pulmonary", reaction =   "Shortness of breath/Difficulty breathing"))
            reactions.add(Reaction(system = "Pulmonary", reaction =   "Asthma attack"))
            reactions.add(Reaction(system = "Pulmonary", reaction =   "Cough"))
            reactions.add(Reaction(system = "Pulmonary", reaction =   "Coughing blood/Haemoptysis"))
            reactions.add(Reaction(system = "Gastrointestinal", reaction =  "Nausea"))
            reactions.add(Reaction(system = "Gastrointestinal", reaction =  "Vomiting"))
            reactions.add(Reaction(system = "Gastrointestinal", reaction =  "Diarrhoea"))
            reactions.add(Reaction(system = "Gastrointestinal", reaction =  "Taste change"))
            reactions.add(Reaction(system = "Kidney", reaction =  "Blood in urine/ Haematuria"))
            reactions.add(Reaction(system = "Cardiac", reaction =  "Chest pain"))
            reactions.add(Reaction(system = "Cardiac", reaction =  "Heart attack"))
            reactions.add(Reaction(system = "Cardiac", reaction =  "Collapse"))
            reactions.add(Reaction(system = "Blood", reaction =  "Neutropenia"))
            reactions.add(Reaction(system = "Blood", reaction =  "Leukopenia"))
            reactions.add(Reaction(system = "Blood", reaction =  "Anaemia"))
            reactions.add(Reaction(system = "Blood", reaction =  "Bleeding"))
            reactions.add(Reaction(system = "Neurological", reaction =  "Confusion"))
            reactions.add(Reaction(system = "Neurological", reaction =  "Numbness"))
            reactions.add(Reaction(system = "Neurological", reaction =  "Tingling"))
            reactions.add(Reaction(system = "Neurological", reaction =  "Seizure"))
            reactions.add(Reaction(system = "Neurological", reaction =  "Hearing loss"))
            reactions.add(Reaction(system = "Musculoskeletal", reaction =  "Myalgia/ muscle pain"))
            reactions.add(Reaction(system = "Musculoskeletal", reaction =  "Weakness"))
            return reactions
        }
    }
}
