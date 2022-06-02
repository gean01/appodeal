export interface GeanAppodealPlugin {
  initializeAppodeal(options: { key: string, isDev: boolean, useTestAds: boolean, consent: boolean }): Promise<boolean>;
  showInterstitial(): Promise<boolean>;
  showBannerBottom(): Promise<boolean>;
  showBannerTop(): Promise<boolean>;
  interstitialIsLoaded(): Promise<boolean>;
  bannerIsLoaded(): Promise<boolean>;
}
