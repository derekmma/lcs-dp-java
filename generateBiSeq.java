double toDetermine = new Double(0);
for (int i = 0; i < 580; i++){
    toDetermine = new Random().nextDouble();
    if(toDetermine < 0.5) {
        X+="1"; }
    else { 
        X+="0"; }
}
for (int i = 0; i < 580; i++){
    toDetermine = new Random().nextDouble();
    if(toDetermine < 0.5) {
        Y+="1"; }
    else { 
        Y+="0"; }
}
for (int i = 0; i < 580; i++){
    toDetermine = new Random().nextDouble();
    if(toDetermine < 0.5) {
        Z+="1"; }
    else { 
        Z+="0"; }
}