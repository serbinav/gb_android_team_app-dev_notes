package com.example.notesvsshoppinglist.di

import com.example.notesvsshoppinglist.repository.*
import com.example.notesvsshoppinglist.ui.notes.NotesViewModel
import com.rino.database.DatabaseModule
import org.koin.dsl.module

val appModule = module {
    // Database
    single { DatabaseModule.getAppDatabase(context = get()) }
    single { DatabaseModule.getNoteGetDao(database = get()) }
    single { DatabaseModule.getNoteSetDao(database = get()) }
    single { DatabaseModule.getChecklistGetDao(database = get()) }
    single { DatabaseModule.getChecklistSetDao(database = get()) }

    // Repository
    single<NoteRepository> { DummyNoteRepositoryImpl() }
    single<ChecklistRepository> { ChecklistRepositoryImpl(checklistGetDao = get(), checklistSetDao = get()) }

    // View model
    single { NotesViewModel(noteRepository = get()) }
}