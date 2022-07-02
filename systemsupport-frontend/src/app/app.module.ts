import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SuggestionComponent } from './suggestion/suggestion.component';
import { FuzzyComponent } from './fuzzy/fuzzy.component';
import { BayesComponent } from './bayes/bayes.component';
import { NavBarComponent } from './core/nav-bar/nav-bar.component';
import { HomeComponent } from './core/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    SuggestionComponent,
    FuzzyComponent,
    BayesComponent,
    NavBarComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
