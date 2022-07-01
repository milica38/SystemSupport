import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './core/nav-bar/nav-bar.component';
import { SuggestionComponent } from './suggestion/suggestion.component';
import { FuzzyComponent } from './fuzzy/fuzzy.component';
import { BayesComponent } from './bayes/bayes.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    SuggestionComponent,
    FuzzyComponent,
    BayesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
