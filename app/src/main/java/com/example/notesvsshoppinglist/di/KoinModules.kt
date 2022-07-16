package com.example.notesvsshoppinglist.di

import com.example.notesvsshoppinglist.repository.ChecklistRepository
import com.example.notesvsshoppinglist.repository.ChecklistRepositoryImpl
import com.example.notesvsshoppinglist.repository.NoteRepository
import com.example.notesvsshoppinglist.repository.NoteRepositoryImpl
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
    single<NoteRepository> { NoteRepositoryImpl(noteGetDao = get(), noteSetDao = get()) }
    single<ChecklistRepository> { ChecklistRepositoryImpl(checklistGetDao = get(), checklistSetDao = get()) }
}