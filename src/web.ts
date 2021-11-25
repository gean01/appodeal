import { WebPlugin } from '@capacitor/core';

import type { GeanAppodealPlugin } from './definitions';

export class GeanAppodealWeb extends WebPlugin implements GeanAppodealPlugin {
  async showInterstitial(options: { value: string }): Promise<{ value: string }> {
    console.log('showInterstitial', options);
    return options;
  }

  async setTesting(options: { value: boolean }): Promise<{ value: boolean }> {
    console.log('setTesting', options);
    return options;
  }

  async setKey(options: { key: string }): Promise<{ key: string }> {
    console.log('setKey', options);
    return options;
  }
}
