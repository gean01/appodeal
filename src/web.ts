import { WebPlugin } from '@capacitor/core';

import type { GeanAppodealPlugin } from './definitions';

export class GeanAppodealWeb extends WebPlugin implements GeanAppodealPlugin {
  async initializeAppodeal(options: { key: string, useTestAds: boolean }): Promise<boolean> {
    console.log(options);
    return true;
  }

  async showInterstitial(): Promise<boolean> {
    console.log('showInterstitial');
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
