package com.example.notesvsshoppinglist.di

import com.example.notesvsshoppinglist.repository.*
import com.example.notesvsshoppinglist.ui.calendar.CalendarViewModel
import com.example.notesvsshoppinglist.ui.checklist.ChecklistViewModel
import com.example.notesvsshoppinglist.ui.checklist.EditChecklistViewModel
import com.example.notesvsshoppinglist.ui.notes.EditNotesViewModel
import com.example.notesvsshoppinglist.ui.notes.NotesViewModel
import com.rino.database.DatabaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
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
    single<ChecklistRepository> { DummyChecklistRepositoryImpl() }

    // View model
    viewModel { NotesViewModel(noteRepository = get()) }
    viewModel { ChecklistViewModel(checklistRepository = get()) }
    viewModel { CalendarViewModel() }
    viewModel { EditChecklistViewModel() }
    viewModel { EditNotesViewModel() }
}