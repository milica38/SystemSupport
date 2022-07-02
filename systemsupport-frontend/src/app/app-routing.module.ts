import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BayesComponent } from './bayes/bayes.component';
import { HomeComponent } from './core/home/home.component';
import { FuzzyComponent } from './fuzzy/fuzzy.component';
import { SuggestionComponent } from './suggestion/suggestion.component';

const routes: Routes = [
  {path:'suggestion',component: SuggestionComponent},
  {path:'fuzzy',component: FuzzyComponent},
  {path:'bayes',component: BayesComponent},
  { path: 'home', component: HomeComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
