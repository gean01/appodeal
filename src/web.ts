import { WebPlugin } from '@capacitor/core';

import type { GeanAppodealPlugin } from './definitions';

export class GeanAppodealWeb extends WebPlugin implements GeanAppodealPlugin {
  async initializeAppodeal(options: { key: string, isDev: boolean, useTestAds: boolean, consent: boolean }): Promise<boolean> {
    console.log(options);
    return true;
  }

  async showInterstitial(): Promise<boolean> {
    console.log('showInterstitial');
    return true;
  }

  async showBannerTop(): Promise<boolean> {
    console.log('showBannerTop');
    return true;
  }

  async showBannerBottom(): Promise<boolean> {
    console.log('showBannerBottom');
    return true;
  }

  async interstitialIsLoaded(): Promise<boolean> {
    console.log('interstitialIsLoaded');
    return true;
  }

  async bannerIsLoaded(): Promise<boolean> {
    console.log('bannerIsLoaded');
    return true;
  }

  async startActivity(): Promise<boolean> {
    console.log('startActivity');
    return true;
  }

  // async setTesting(options: { value: boolean }): Promise<{ value: boolean }> {
  //   console.log('setTesting', options);
  //   return options;
  // }

  // async setKey(options: { key: string }): Promise<{ key: string }> {
  //   console.log('setKey', options);
  //   return options;
  // }
}
