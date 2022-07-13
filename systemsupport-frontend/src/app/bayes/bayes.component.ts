import { Component, OnInit } from '@angular/core';
import { BayesService } from '../services/bayes.service';

@Component({
  selector: 'app-bayes',
  templateUrl: './bayes.component.html',
  styleUrls: ['./bayes.component.css']
})
export class BayesComponent implements OnInit {

  constructor(public service: BayesService) { }

  results: any[] = [];
  selectedSymptome: string = "";
  resultBox: boolean = false;

  ngOnInit(): void {
  }

  onSelected(value: any): void {
    this.selectedSymptome = value;
  }

  onDamage(): void {
    let params = {
      symptome: this.selectedSymptome
    }
    this.service.getDamages(params).subscribe((response: any) => {
      console.log(response)
     let filtrirano=[];
      for(let i=0;i < response.length;i++){
     
        // if(this.selectedSymptome=="app_not_responding" && (response[i].failure == "RAM DAMAGE" || response[i].failure == "OS DAMAGE") ){
        //   filtrirano.push(response[i]);
        //  }
         
        // if(this.selectedSymptome=="apps_cant_install" && response[i].failure == "RAM DAMAGE" || response[i].failure == "HDD DAMAGE" ){
        //   filtrirano.push(response[i]);
        //  }

        switch(this.selectedSymptome){
          case "app_not_responding":{
            if(response[i].failure == "RAM DAMAGE" || response[i].failure == "OS DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "apps_cant_install":{
            if(response[i].failure == "RAM DAMAGE" || response[i].failure == "HDD DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "blank_screen":{
            if(response[i].failure == "POWER SUPPLY DAMAGE" || response[i].failure == "GPU DAMAGE"  || response[i].failure == "MONITOR DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "overheating":{
            if(response[i].failure == "COOLING SYSTEM DAMAGE" || response[i].failure == "CPU DAMAGE"  ){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "disk_wont_load":{
            if(response[i].failure == "DISK PHYISICAL DAMAGE" || response[i].failure == "BIOS DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "pc_randomly_shuts_down":{
            if(response[i].failure == "POWER SUPPLY DAMAGE" || response[i].failure == "MOTHERBOARD DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "pc_crash_before_loading_os":{
            if(response[i].failure == "OS DAMAGE" || response[i].failure == "HDD DAMAGE"  || response[i].failure == "RAM DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "sound_isnt_working":{
            if(response[i].failure == "SPEAKERS DAMAGE" || response[i].failure == "BLUETOOTH CONNECTION DAMAGE"  || response[i].failure == "SOUND CARD DAMAGE"  || response[i].failure == "PORT DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "wifi_disconnecting":{
            if(response[i].failure == "NETWORK CARD DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "slow_pc":{
            if(response[i].failure == "ANTIVIRUS DAMAGE" || response[i].failure == "STARTUP PROGRAMS DAMAGE" || response[i].failure == "HDD DAMAGE" || response[i].failure == "RAM DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }
          case "slow_internet":{
            if(response[i].failure == "POWER SUPPLY DAMAGE" || response[i].failure == "ROUTER DAMAGE"  || response[i].failure == "PROVIDER DAMAGE"  || response[i].failure == "CABLE DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

          case "frozen_screen":{
            if(response[i].failure == "HDD DAMAGE" || response[i].failure == "OS DAMAGE" || response[i].failure == "RAM DAMAGE" || response[i].failure == "DRIVER DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

          case "computer_beeps":{
            if(response[i].failure == "RAM DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

          case "blue_screen_of_death":{
            if(response[i].failure == "COOLING SYSTEM DAMAGE" || response[i].failure == "RAM DAMAGE" || response[i].failure == "MOTHERBOARD DAMAGE" || response[i].failure == "HDD DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

          case "mouse_malfunction":{
            if(response[i].failure == "CABLE DAMAGE" || response[i].failure == "DRIVER DAMAGE" || response[i].failure == "PORT DAMAGE" || response[i].failure == "PHYSICAL DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

          case "usb_port_malfunction":{
            if(response[i].failure == "PHYSICAL DAMAGE" || response[i].failure == "PINS DAMAGE" || response[i].failure == "DRIVER DAMAGE") {
              filtrirano.push(response[i]);
            }
            break;
          }
          
          case "usb_wont_load":{
            if(response[i].failure == "ANTIVIRUS DAMAGE" || response[i].failure == "HDD DAMAGE" || response[i].failure == "DRIVER DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

          
          case "keyboard_malfunction":{
            if(response[i].failure == "PHYSICAL DAMAGE" || response[i].failure == "DRIVER DAMAGE" || response[i].failure == "BLUETOOTH CONNECTION DAMAGE" || response[i].failure == "PORT DAMAGE"){
              filtrirano.push(response[i]);
            }
            break;
          }

        }

      }


      
      this.results=filtrirano;

      

    })
    this.resultBox = true;
  }

}
